package io.litedb.metadata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.litedb.filesystem.LiteFile;
import io.litedb.filesystem.LiteStorageEngine;
import io.litedb.metadata.tables.AbstractMetaTable;
import io.litedb.metadata.tables.ColumnsMetaMetaTable;
import io.litedb.metadata.tables.TablesMetaMetaTable;
import io.litedb.scanning.FullTableScan;
import io.litedb.scanning.WritableScan;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.IntegerData;
import io.litedb.tuples.data.VarcharData;
import io.litedb.tuples.data.info.TupleDatumInfo;

public class MetadataOverseer {
    private LiteStorageEngine storageEngine;
    private static MetadataOverseer INSTANCE;

    private Map<String, TableSchema> tableSchemata = new HashMap<>();

    private Map<String, AbstractMetaTable> metaTables = new HashMap<>();
    private Map<String, LiteFile> metaTableFiles = new HashMap<>();

    private MetadataOverseer(LiteStorageEngine storageEngine) {
        this.storageEngine = storageEngine;
        this.metaTables.put("tables_meta", new TablesMetaMetaTable());
        this.metaTables.put("columns_meta", new ColumnsMetaMetaTable());

        for (String mTableName: this.metaTables.keySet()) {
            tableSchemata.put(mTableName, this.metaTables.get(mTableName).getSchema());
        }

        for (String mTableName : this.metaTables.keySet()) {
            LiteFile file = this.storageEngine.getFile(String.format("%s.lt", mTableName));
            metaTableFiles.put(mTableName, file);
            addTable(mTableName, this.metaTables.get(mTableName).getSchema());
        }
    }

    public static MetadataOverseer getInstance(LiteStorageEngine storageEngine) {
        if (INSTANCE == null) {
            INSTANCE = new MetadataOverseer(storageEngine);
        }
        return INSTANCE;
    }

    public void addTable(String tableName, TableSchema rowSchema) {
        try {
            attemptAddColumnsMetaEntries(tableName, rowSchema);
            attemptAddTablesMetaEntries(tableName, rowSchema);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to add table: " + e.getMessage());
        }
    }

    private void attemptAddColumnsMetaEntries(String tableName, TableSchema rowSchema) throws IOException {
        WritableScan columnsMetaScan = new FullTableScan("columns_meta", storageEngine, this);
    
        columnsMetaScan.begin();
        LiteRow currentRow;

        while ((currentRow = columnsMetaScan.readRow()) != null) {
            String currentColumnName = currentRow.getData("column_name").toString();
            String currentTableName = currentRow.getData("table_name").toString();
            if (currentTableName.equals(tableName) && rowSchema.hasField(currentColumnName)) {
                throw new RuntimeException(String.format("Column %s already exists for table %s", currentColumnName, tableName));
            }
            columnsMetaScan.next();
        }

        for (String field: rowSchema.getFields()) {
            TupleDatumInfo info = rowSchema.getFieldInfo(field);
            int columnSize = info.getSize();
            String columnType = info.getTypeString();

            LiteRow columnData = new LiteRow();
            columnData.setData("column_name", new VarcharData(field, 100));
            columnData.setData("table_name", new VarcharData(tableName, 100));
            columnData.setData("column_type", new VarcharData(columnType, 100));
            columnData.setData("column_size", new IntegerData(columnSize));
        
            columnsMetaScan.insert(columnData);
        }
    }

    private void attemptAddTablesMetaEntries(String tableName, TableSchema rowSchema) throws IOException {
        this.tableSchemata.put(tableName, rowSchema);

        WritableScan tablesMetaScan = new FullTableScan("tables_meta", storageEngine, this);

        tablesMetaScan.begin();
        LiteRow currentRow;

        while ((currentRow = tablesMetaScan.readRow()) != null) {
            if (currentRow.getData("table_name").toString().equals(tableName)) {
                throw new RuntimeException(String.format("Table %s already exists", tableName));
            }
            tablesMetaScan.next();
        }

        LiteRow tableMetadata = new LiteRow();
        tableMetadata.setData("table_name", new VarcharData(tableName, 200));
        tableMetadata.setData("row_size",
                new IntegerData(this.tableSchemata.get(tableName).getSize()));

        tablesMetaScan.insert(tableMetadata);
    }

    public TableSchema getTableSchema(String tableName) {
        return this.tableSchemata.get(tableName);
    }
}
