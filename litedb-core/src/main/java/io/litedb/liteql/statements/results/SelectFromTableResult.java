package io.litedb.liteql.statements.results;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import io.litedb.tuples.LiteRow;

public class SelectFromTableResult implements LiteQLResult {
    private List<LiteRow> rows = new ArrayList<>();

    private List<String> fields;

    public SelectFromTableResult(List<String> fields) {
        this.fields = fields;
    }

    @Override
    public List<LiteRow> getRows() {
        return rows;
    }

    public void addRow(LiteRow row) {
        rows.add(row);
    }
    
    public String toString() {
        StringJoiner headerRow = new StringJoiner(", ");

        for (String fieldName: fields) {
            headerRow.add(fieldName);
        }

        StringJoiner rowStrings = new StringJoiner("\n");

        for (LiteRow row: rows) {
            StringJoiner rowString = new StringJoiner(", ");
            for (String field: fields) {
                rowString.add(row.getData(field).toString());
            }
            rowStrings.add(rowString.toString());
        }

        return String.format("%s\n%s", headerRow.toString(), rowStrings.toString());
    }
}
