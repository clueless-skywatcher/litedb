package io.litedb.scanning;

import java.io.IOException;
import java.util.List;

import io.litedb.tuples.LTRow;
import io.litedb.tuples.LTSchema;

public class LTProjectionScan implements LTDBScan {
    private LTDBScan scan;
    private List<String> fields;

    public LTProjectionScan(LTDBScan scan, List<String> fields) {
        this.scan = scan;
        this.fields = fields;
    }

    @Override
    public void begin() throws IOException {
        scan.begin();
    }

    @Override
    public boolean next() throws IOException {
        return scan.next();
    }

    @Override
    public LTRow readRow() {
        LTRow row = new LTRow();
        LTRow scanRow = scan.readRow();

        if (scanRow == null) return null;

        for (String field: fields) {
            row.setData(field, scanRow.getData(field));
        }

        return row;
    }

    @Override
    public LTSchema getTableSchema() {
        LTSchema schema = new LTSchema();
        for (String field: fields) {
            schema.addField(field, scan.getTableSchema().getFieldInfo(field));
        }
        return schema;
    }

}
