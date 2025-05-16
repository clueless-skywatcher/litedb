package io.litedb.scanning;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.TupleData;
import io.litedb.tuples.data.info.TupleDatumInfo;

public class CartesianProductScan implements DBScan {
    private DBScan left;
    private DBScan right;

    public CartesianProductScan(DBScan left, DBScan right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void begin() throws IOException {
        left.begin();
        right.begin();
    }

    @Override
    public boolean next() throws IOException {
        if (right.next()) {
            return true;
        }
        right.begin();
        return left.next();
    }

    @Override
    public LiteRow readRow() {
        LiteRow rowL = left.readRow();
        LiteRow rowR = right.readRow();

        if (rowL == null || rowR == null) {
            return null;
        }

        Map<String, TupleData<?>> data = new HashMap<>();

        for (String field: rowL.getFields()) {
            if (rowR.getFields().contains(field)) {
                data.put(String.format("%s_0", field), rowL.getData(field));
                data.put(String.format("%s_1", field), rowR.getData(field));
            } else {
                data.put(field, rowL.getData(field));
            }
        }

        for (String field: rowR.getFields()) {
            data.put(field, rowR.getData(field));
        }

        return new LiteRow(data);
    }

    @Override
    public TableSchema getTableSchema() {
        TableSchema schemaL = left.getTableSchema();
        TableSchema schemaR = right.getTableSchema();

        Map<String, TupleDatumInfo> fields = new HashMap<>();

        for (String field: schemaL.getFields()) {
            if (schemaR.getFields().contains(field)) {
                fields.put(String.format("%s_0", field), schemaL.getFieldInfo(field));
                fields.put(String.format("%s_1", field), schemaR.getFieldInfo(field));
            } else {
                fields.put(field, schemaL.getFieldInfo(field));
            }
        }

        for (String field: schemaR.getFields()) {
            fields.put(field, schemaR.getFieldInfo(field));
        }

        return new TableSchema(fields);
    }
}
