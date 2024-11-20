package io.litedb.liteql.statements.results;

import java.util.List;

import io.litedb.tuples.LiteRow;
import lombok.Getter;
import lombok.Setter;

public class UpdateTableResult implements LiteQLResult {
    private @Getter @Setter long timeTaken;
    private String tableName;

    public UpdateTableResult(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<LiteRow> getRows() {
        return List.of();
    }

    public String toString() {
        return String.format("UPDATE %s completed in %d ms", tableName, timeTaken);
    }
}
