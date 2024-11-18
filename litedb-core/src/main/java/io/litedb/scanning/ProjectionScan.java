package io.litedb.scanning;

import java.io.IOException;
import java.util.List;

import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;

public class ProjectionScan implements DBScan {
    private DBScan scan;
    private List<String> fields;

    public ProjectionScan(DBScan scan, List<String> fields) {
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
    public LiteRow readRow() {
        LiteRow row = new LiteRow();
        LiteRow scanRow = scan.readRow();

        if (scanRow == null) return null;

        for (String field: fields) {
            row.setData(field, scanRow.getData(field));
        }

        return row;
    }

    @Override
    public TableSchema getTableSchema() {
        return scan.getTableSchema();
    }

}
