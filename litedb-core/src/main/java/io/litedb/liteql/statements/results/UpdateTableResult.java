package io.litedb.liteql.statements.results;

import java.util.List;

import io.litedb.tuples.LTRow;
import lombok.Getter;
import lombok.Setter;

public class UpdateTableResult implements LiteQLResult {
    private @Getter @Setter long timeTaken;
    private String tableName;
    private int rowsUpdated;

    public UpdateTableResult(String tableName, int rowsUpdated) {
        this.tableName = tableName;
        this.rowsUpdated = rowsUpdated;
    }

    @Override
    public List<LTRow> getRows() {
        return List.of();
    }

    public String toString() {
        return String.format("UPDATE %s: %d rows affected in %d ms", tableName, rowsUpdated, timeTaken);
    }
}
