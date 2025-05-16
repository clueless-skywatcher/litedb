package io.litedb.scanning;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.litedb.buffer.LTBufferManager;
import io.litedb.filesystem.LTBlockIdentifier;
import io.litedb.filesystem.LTDBPage;
import io.litedb.filesystem.LTFile;
import io.litedb.filesystem.LTStorageEngine;
import io.litedb.liteql.LTPredicate;
import io.litedb.metadata.LTMetadataOverseer;
import io.litedb.tuples.LTRow;
import io.litedb.tuples.LTSchema;
import io.litedb.tuples.data.LTTupleData;
import lombok.Getter;

public class LTFullTableScan implements LTWritableScan {
    private @Getter String tableName;
    private @Getter LTStorageEngine storageEngine;
    private @Getter LTBufferManager bufferManager;

    private LTDBPage currentPage;
    private int currentSlotInPage;

    private int currentBlock;

    private LTFile tableFile;
    private @Getter LTSchema tableSchema;

    private boolean moveToFirstDirtySlot;

    private int slotsPerPage;

    public LTFullTableScan(
            String tableName,
            LTStorageEngine engine,
            LTMetadataOverseer metadataOverseer,
            LTBufferManager bufferManager)
            throws IOException {
        this(tableName, engine, metadataOverseer, bufferManager, false);
    }

    public LTFullTableScan(
            String tableName,
            LTStorageEngine engine,
            LTMetadataOverseer metadataOverseer,
            LTBufferManager bufferManager,
            boolean moveToFirstDirtySlot)
            throws IOException {
        this.tableName = tableName;
        this.storageEngine = engine;
        this.moveToFirstDirtySlot = moveToFirstDirtySlot;
        this.bufferManager = bufferManager;
        this.tableFile = this.storageEngine.getFile(tableName + ".lt");
        this.tableSchema = metadataOverseer.getTableSchema(tableName);
    }

    public void begin() throws IOException {
        moveToBlock(0);
        this.slotsPerPage = currentPage.getContents().length / tableSchema.getSize();

        if (moveToFirstDirtySlot) {
            while (!isDirtySlot()) {
                if (!next()) {
                    break;
                }
            }
        }
    }

    public void moveToBlock(int blockNumber) throws IOException {
        LTBlockIdentifier block = new LTBlockIdentifier(tableFile.getFileName(), blockNumber, LTFile.BLOCK_SIZE);
        currentPage = bufferManager.pinBuffer(block).getPage();
        currentBlock = blockNumber;
        currentSlotInPage = 0;
    }

    public boolean isDirtySlot() {
        return currentPage.getByte(currentSlotInPage * tableSchema.getSize()) == 1;
    }

    public LTRow readRow() {
        LTRow row = new LTRow();

        if (!isDirtySlot()) {
            return null;
        }

        for (String field : this.tableSchema.getFields()) {
            int fieldPosition = currentSlotInPage * tableSchema.getSize() + 1 + this.tableSchema.getOffset(field);
            row.setData(field,
                    currentPage.getData(fieldPosition, this.tableSchema.getFieldInfo(field)));
        }
        return row;
    }

    public boolean next() throws IOException {
        while (currentSlotInPage < slotsPerPage) {
            currentSlotInPage++;
            if (isDirtySlot()) {
                return true;
            }
        }

        if (currentBlock < tableFile.getBlockCount() - 1) {
            moveToBlock(currentBlock + 1);
            if (isDirtySlot()) {
                return true;
            }
            return next();
        }

        return false;
    }

    @Override
    public void insert(LTRow row) throws IOException {
        begin();
        while (currentSlotInPage < slotsPerPage) {
            if (!isDirtySlot()) {
                insertIntoSlot(row, currentSlotInPage);
                return;
            } else {
                currentSlotInPage++;
            }
        }

        if (currentBlock == tableFile.getBlockCount() - 1) {
            this.tableFile.appendNewBlock(currentBlock + 1);
        }

        moveToBlock(currentBlock + 1);
        insert(row);
    }

    private void insertIntoSlot(LTRow row, int slotNumber) throws IOException {
        int slotPosition = slotNumber * tableSchema.getSize();

        currentPage.setByte(slotPosition, (byte) 1);

        for (String field : row.getFields()) {
            currentPage.setData(slotPosition + 1 + tableSchema.getOffset(field), row.getData(field));
        }

        bufferManager.flushAll();
    }

    @Override
    public int update(Map<String, LTTupleData<?>> data) throws IOException {
        return update(data, List.of());
    }

    private void updateSlot(int slot, Map<String, LTTupleData<?>> data) throws IOException {
        int slotPosition = slot * tableSchema.getSize();

        for (String field : data.keySet()) {
            currentPage.setData(slotPosition + 1 + tableSchema.getOffset(field), data.get(field));
        }
    }

    @Override
    public int update(Map<String, LTTupleData<?>> data, List<LTPredicate> predicates) throws IOException {
        begin();
        LTRow currentRow;
        int rowsUpdated = 0;
        while ((currentRow = readRow()) != null) {
            boolean allSatisfied = true;
            for (LTPredicate predicate : predicates) {
                allSatisfied = allSatisfied && predicate.rowSatisfies(currentRow, tableSchema);
            }
            if (allSatisfied) {
                updateSlot(currentSlotInPage, data);
                rowsUpdated++;
            }
            next();
        }
        bufferManager.flushAll();
        return rowsUpdated;
    }

    @Override
    public int delete() throws IOException {
        return delete(List.of());
    }

    @Override
    public int delete(List<LTPredicate> predicates) throws IOException {
        begin();
        LTRow currentRow;
        int rowsDeleted = 0;
        while ((currentRow = readRow()) != null) {
            boolean allSatisfied = true;
            for (LTPredicate predicate : predicates) {
                allSatisfied = allSatisfied && predicate.rowSatisfies(currentRow, tableSchema);
            }
            if (allSatisfied) {
                deleteSlot(currentSlotInPage);
                rowsDeleted++;
            }
            next();
        }
        return rowsDeleted;
    }

    private void deleteSlot(int slot) throws IOException {
        int slotPosition = slot * tableSchema.getSize();
        currentPage.setByte(slotPosition, (byte) 0);
        bufferManager.flushAll();
    }
}
