package io.litedb.metadata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.litedb.buffer.LTBufferManager;
import io.litedb.filesystem.LTStorageEngine;
import io.litedb.metadata.tables.LTColumnsMetaTable;
import io.litedb.metadata.tables.LTTablesMetaTable;
import io.litedb.scanning.LTFullTableScan;
import io.litedb.scanning.LTWritableScan;
import io.litedb.tuples.LTRow;
import io.litedb.tuples.LTSchema;
import io.litedb.tuples.data.LTIntegerData;
import io.litedb.tuples.data.LTVarcharData;
import io.litedb.tuples.data.info.LTTupleDatumInfo;

public class LTMetadataOverseer {
    private LTStorageEngine storageEngine;
    private LTBufferManager bufferManager;
    private Map<String, LTSchema> schemas = new HashMap<>();

    public LTMetadataOverseer(LTStorageEngine storageEngine, LTBufferManager bufferManager) {
        this.storageEngine = storageEngine;
        this.bufferManager = bufferManager;

        schemas.put(LTColumnsMetaTable.NAME, LTColumnsMetaTable.SCHEMA);
        schemas.put(LTTablesMetaTable.NAME, LTTablesMetaTable.SCHEMA);

        addTable(LTColumnsMetaTable.NAME, LTColumnsMetaTable.SCHEMA);
        addTable(LTTablesMetaTable.NAME, LTTablesMetaTable.SCHEMA);
    }

    public void addTable(String tableName, LTSchema rowSchema) {
        try {
            attemptAddColumnsMetaEntries(tableName, rowSchema);
            attemptAddTablesMetaEntries(tableName, rowSchema);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add table: " + e.getMessage());
        }
    }

    private void attemptAddColumnsMetaEntries(String tableName, LTSchema rowSchema) throws IOException {
        LTWritableScan columnsMetaScan = new LTFullTableScan(
            LTColumnsMetaTable.NAME, 
            storageEngine, 
            this, 
            bufferManager
        );

        columnsMetaScan.begin();
        LTRow currentRow;

        while ((currentRow = columnsMetaScan.readRow()) != null) {
            String currentColumnName = currentRow.getData("column_name").toString();
            String currentTableName = currentRow.getData("table_name").toString();
            if (currentTableName.equals(tableName) && rowSchema.hasField(currentColumnName)) {
                return;
            }
            columnsMetaScan.next();
        }

        for (String field : rowSchema.getFields()) {
            LTTupleDatumInfo info = rowSchema.getFieldInfo(field);
            int columnSize = info.getSize();
            String columnType = info.getTypeString();

            LTRow columnData = new LTRow();
            columnData.setData("column_name", new LTVarcharData(field, 100));
            columnData.setData("table_name", new LTVarcharData(tableName, 100));
            columnData.setData("column_type", new LTVarcharData(columnType, 50));
            columnData.setData("column_size", new LTIntegerData(columnSize));

            columnsMetaScan.insert(columnData);
        }
    }

    private void attemptAddTablesMetaEntries(String tableName, LTSchema rowSchema) throws IOException {
        LTWritableScan tablesMetaScan = new LTFullTableScan(
            LTTablesMetaTable.NAME, 
            storageEngine, 
            this,
            bufferManager
        );

        tablesMetaScan.begin();
        LTRow currentRow;

        while ((currentRow = tablesMetaScan.readRow()) != null) {
            if (currentRow.getData("table_name").toString().equals(tableName)) {
                return;
            }
            tablesMetaScan.next();
        }

        LTRow tableMetadata = new LTRow();
        tableMetadata.setData("table_name", new LTVarcharData(tableName, 100));
        tableMetadata.setData("row_size",
                new LTIntegerData(getTableSchema(tableName).getSize()));

        tablesMetaScan.insert(tableMetadata);
    }

    public LTSchema getTableSchema(String tableName) throws IOException {
        if (schemas.containsKey(tableName)) {
            return schemas.get(tableName);
        }

        LTSchema schema = new LTSchema();

        LTWritableScan columnsMetaScan = new LTFullTableScan(
            LTColumnsMetaTable.NAME, 
            storageEngine, 
            this,
            bufferManager
        );

        columnsMetaScan.begin();
        LTRow currentRow;

        while ((currentRow = columnsMetaScan.readRow()) != null) {
            if (currentRow.getData("table_name").toString().equals(tableName)) {
                String columnName = currentRow.getData("column_name").toString();
                String columnType = currentRow.getData("column_type").toString();
                LTTupleDatumInfo info = LTTupleDatumInfo.inferTypeFromString(columnType);

                schema.addField(columnName, info);
            }
            columnsMetaScan.next();
        }

        return schema;
    }
}
