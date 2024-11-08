package io.litedb.scanning;

import java.io.IOException;

import io.litedb.filesystem.LiteFile;
import io.litedb.filesystem.LitePage;
import io.litedb.filesystem.LiteStorageEngine;
import io.litedb.metadata.MetadataOverseer;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import lombok.Getter;

public class FullTableScan implements WritableScan {
    private @Getter String tableName;
    private @Getter LiteStorageEngine storageEngine;

    private LitePage currentPage;
    private int currentSlotInPage;

    private int currentBlock;

    private LiteFile tableFile;
    private TableSchema tableSchema;

    private int slotsPerPage;

    public FullTableScan(String tableName, LiteStorageEngine engine, MetadataOverseer metadataOverseer) throws IOException {
        this.tableName = tableName;
        this.storageEngine = engine;

        this.tableFile = this.storageEngine.getFile(tableName + ".lt");
        this.tableSchema = metadataOverseer.getTableSchema(tableName);
    }

    public void begin() throws IOException {
        moveToBlock(0);
        this.slotsPerPage = currentPage.getContents().length / tableSchema.getSize();
    }

    public void moveToBlock(int blockNumber) throws IOException {
        currentPage = this.tableFile.readBlock(blockNumber);
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

        for (String field: this.tableSchema.getFields()) {
            int fieldPosition = currentSlotInPage * tableSchema.getSize() + 1 + this.tableSchema.getOffset(field);
            row.setData(field, 
                currentPage.getData(fieldPosition, this.tableSchema.getFieldInfo(field))
            );
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
        while (currentSlotInPage < slotsPerPage) {
            if (!isDirtySlot()) {
                insertIntoSlot(row, currentSlotInPage);;
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

        for (String field: row.getFields()) {
            currentPage.setData(slotPosition + 1 + tableSchema.getOffset(field), row.getData(field));
        }

        this.tableFile.writeBlock(currentBlock, currentPage);
    }
}
