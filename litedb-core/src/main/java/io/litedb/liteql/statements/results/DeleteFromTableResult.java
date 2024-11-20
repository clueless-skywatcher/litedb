package io.litedb.liteql.statements.results;

import java.util.List;

import io.litedb.tuples.LiteRow;
import lombok.Getter;
import lombok.Setter;

public class DeleteFromTableResult implements LiteQLResult {
    private String tableName;
    private @Getter @Setter long timeTaken;

    public DeleteFromTableResult(String tableName) {
        this.tableName = tableName;
    }

    public String toString() {
        return String.format("DELETE FROM %s completed in %d ms", tableName, timeTaken);
    }

    @Override
    public List<LiteRow> getRows() {
        return List.of();
    }    
}
