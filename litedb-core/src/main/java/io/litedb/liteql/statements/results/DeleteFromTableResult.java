package io.litedb.liteql.statements.results;

import java.util.List;

import io.litedb.tuples.LiteRow;
import lombok.Getter;
import lombok.Setter;

public class DeleteFromTableResult implements LiteQLResult {
    private String tableName;
    private int rowsDeleted;
    private @Getter @Setter long timeTaken;

    public DeleteFromTableResult(String tableName, int rowsDeleted) {
        this.tableName = tableName;
        this.rowsDeleted = rowsDeleted;
    }

    public String toString() {
        return String.format("DELETE FROM %s: %d rows affected in %d ms", tableName, rowsDeleted, timeTaken);
    }

    @Override
    public List<LiteRow> getRows() {
        return List.of();
    }    
}
