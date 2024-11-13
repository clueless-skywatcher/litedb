package io.litedb.metadata;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.litedb.filesystem.LiteStorageEngine;
import io.litedb.metadata.tables.ColumnsMetaMetaTable;
import io.litedb.metadata.tables.TablesMetaMetaTable;
import io.litedb.scanning.FullTableScan;
import io.litedb.scanning.WritableScan;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.IntegerData;
import io.litedb.tuples.data.VarcharData;
import io.litedb.tuples.data.info.BooleanInfo;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class MetadataOverseer {
    private LiteStorageEngine storageEngine;
    private Map<String, TableSchema> schemas = new HashMap<>();

    public MetadataOverseer(LiteStorageEngine storageEngine) {
        this.storageEngine = storageEngine;

        schemas.put(ColumnsMetaMetaTable.NAME, ColumnsMetaMetaTable.SCHEMA);
        schemas.put(TablesMetaMetaTable.NAME, TablesMetaMetaTable.SCHEMA);

        addTable(ColumnsMetaMetaTable.NAME, ColumnsMetaMetaTable.SCHEMA);
        addTable(TablesMetaMetaTable.NAME, TablesMetaMetaTable.SCHEMA);
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
        WritableScan columnsMetaScan = new FullTableScan(ColumnsMetaMetaTable.NAME, storageEngine, this);

        columnsMetaScan.begin();
        LiteRow currentRow;

        while ((currentRow = columnsMetaScan.readRow()) != null) {
            String currentColumnName = currentRow.getData("column_name").toString();
            String currentTableName = currentRow.getData("table_name").toString();
            if (currentTableName.equals(tableName) && rowSchema.hasField(currentColumnName)) {
                throw new RuntimeException(
                        String.format("Column %s already exists for table %s", currentColumnName, tableName));
            }
            columnsMetaScan.next();
        }

        for (String field : rowSchema.getFields()) {
            TupleDatumInfo info = rowSchema.getFieldInfo(field);
            int columnSize = info.getSize();
            String columnType = info.getTypeString();

            LiteRow columnData = new LiteRow();
            columnData.setData("column_name", new VarcharData(field, 100));
            columnData.setData("table_name", new VarcharData(tableName, 100));
            columnData.setData("column_type", new VarcharData(columnType, 50));
            columnData.setData("column_size", new IntegerData(columnSize));

            columnsMetaScan.insert(columnData);
        }
    }

    private void attemptAddTablesMetaEntries(String tableName, TableSchema rowSchema) throws IOException {
        WritableScan tablesMetaScan = new FullTableScan(TablesMetaMetaTable.NAME, storageEngine, this);

        tablesMetaScan.begin();
        LiteRow currentRow;

        while ((currentRow = tablesMetaScan.readRow()) != null) {
            if (currentRow.getData("table_name").toString().equals(tableName)) {
                throw new RuntimeException(String.format("Table %s already exists", tableName));
            }
            tablesMetaScan.next();
        }

        LiteRow tableMetadata = new LiteRow();
        tableMetadata.setData("table_name", new VarcharData(tableName, 100));
        tableMetadata.setData("row_size",
                new IntegerData(getTableSchema(tableName).getSize()));

        tablesMetaScan.insert(tableMetadata);
    }

    public TableSchema getTableSchema(String tableName) throws IOException {
        if (schemas.containsKey(tableName)) {
            return schemas.get(tableName);
        }

        TableSchema schema = new TableSchema();

        WritableScan columnsMetaScan = new FullTableScan(ColumnsMetaMetaTable.NAME, storageEngine, this);

        columnsMetaScan.begin();
        LiteRow currentRow;

        while ((currentRow = columnsMetaScan.readRow()) != null) {
            if (currentRow.getData("table_name").toString().equals(tableName)) {
                String columnName = currentRow.getData("column_name").toString();
                String columnType = currentRow.getData("column_type").toString();
                TupleDatumInfo info = null;
                switch (columnType.toLowerCase()) {
                    case "integer":
                        info = new IntegerInfo();
                        break;
                    case "boolean":
                        info = new BooleanInfo();
                        break;
                    default:
                        if (columnType.matches("varchar\\([0-9]+\\)")) {
                            Pattern pattern = Pattern.compile("-?\\d+");
                            Matcher matcher = pattern.matcher(columnType);
                            int charLength;
                            while (matcher.find()) {
                                charLength = Integer.parseInt(matcher.group());
                                info = new VarcharInfo(charLength);
                                break;
                            }
                        } else {
                            throw new RuntimeException("Invalid columnType read from file: " + columnType);
                        }
                        break;
                }

                schema.addField(columnName, info);
            }
            columnsMetaScan.next();
        }

        return schema;
    }
}
