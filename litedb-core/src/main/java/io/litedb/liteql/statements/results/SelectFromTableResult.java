package io.litedb.liteql.statements.results;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import io.litedb.tuples.LTRow;
import lombok.Getter;
import lombok.Setter;

public class SelectFromTableResult implements LiteQLResult {
    private List<LTRow> rows = new ArrayList<>();
    private List<String> fields;

    private @Getter @Setter long timeTaken;
    private String tableName;

    public SelectFromTableResult(String tableName, List<String> fields) {
        this.fields = fields;
        this.tableName = tableName;
    }

    @Override
    public List<LTRow> getRows() {
        return rows;
    }

    public void addRow(LTRow row) {
        rows.add(row);
    }

    public String toString() {
        StringJoiner headerRow = new StringJoiner(", ");

        for (String fieldName : fields) {
            headerRow.add(fieldName);
        }

        StringJoiner rowStrings = new StringJoiner("\n");

        for (LTRow row : rows) {
            StringJoiner rowString = new StringJoiner(", ");
            for (String field : fields) {
                rowString.add(row.getData(field).toString());
            }
            rowStrings.add(rowString.toString());
        }

        return String.format("%s\n%s\nSELECT FROM %s: %d rows fetched in %d ms",
                headerRow.toString(), rowStrings.toString(), tableName, 
                rows.size(), timeTaken
            );
    }
}
