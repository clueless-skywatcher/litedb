package io.litedb.scanning;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.litedb.buffer.LiteBufferManager;
import io.litedb.filesystem.BlockIdentifier;
import io.litedb.filesystem.ILiteDBPage;
import io.litedb.filesystem.LiteFile;
import io.litedb.filesystem.LiteStorageEngine;
import io.litedb.liteql.QueryPredicate;
import io.litedb.metadata.MetadataOverseer;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.TupleData;
import lombok.Getter;

public class FullTableScan implements WritableScan {
    private @Getter String tableName;
    private @Getter LiteStorageEngine storageEngine;
    private @Getter LiteBufferManager bufferManager;

    private ILiteDBPage currentPage;
    private int currentSlotInPage;

    private int currentBlock;

    private LiteFile tableFile;
    private @Getter TableSchema tableSchema;

    private boolean moveToFirstDirtySlot;

    private int slotsPerPage;

    public FullTableScan(
            String tableName,
            LiteStorageEngine engine,
            MetadataOverseer metadataOverseer,
            LiteBufferManager bufferManager)
            throws IOException {
        this(tableName, engine, metadataOverseer, bufferManager, false);
    }

    public FullTableScan(
            String tableName,
            LiteStorageEngine engine,
            MetadataOverseer metadataOverseer,
            LiteBufferManager bufferManager,
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
        BlockIdentifier block = new BlockIdentifier(tableFile.getFileName(), blockNumber, LiteFile.BLOCK_SIZE);
        currentPage = bufferManager.pinBuffer(block).getPage();
        currentBlock = blockNumber;
        currentSlotInPage = 0;
    }

    public boolean isDirtySlot() {
        return currentPage.getByte(currentSlotInPage * tableSchema.getSize()) == 1;
    }

    public LiteRow readRow() {
        LiteRow row = new LiteRow();

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
    public void insert(LiteRow row) throws IOException {
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

    private void insertIntoSlot(LiteRow row, int slotNumber) throws IOException {
        int slotPosition = slotNumber * tableSchema.getSize();

        currentPage.setByte(slotPosition, (byte) 1);

        for (String field : row.getFields()) {
            currentPage.setData(slotPosition + 1 + tableSchema.getOffset(field), row.getData(field));
        }

        bufferManager.flushAll();
    }

    @Override
    public int update(Map<String, TupleData<?>> data) throws IOException {
        return update(data, List.of());
    }

    private void updateSlot(int slot, Map<String, TupleData<?>> data) throws IOException {
        int slotPosition = slot * tableSchema.getSize();

        for (String field : data.keySet()) {
            currentPage.setData(slotPosition + 1 + tableSchema.getOffset(field), data.get(field));
        }
    }

    @Override
    public int update(Map<String, TupleData<?>> data, List<QueryPredicate> predicates) throws IOException {
        begin();
        LiteRow currentRow;
        int rowsUpdated = 0;
        while ((currentRow = readRow()) != null) {
            boolean allSatisfied = true;
            for (QueryPredicate predicate : predicates) {
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
    public int delete(List<QueryPredicate> predicates) throws IOException {
        begin();
        LiteRow currentRow;
        int rowsDeleted = 0;
        while ((currentRow = readRow()) != null) {
            boolean allSatisfied = true;
            for (QueryPredicate predicate : predicates) {
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
