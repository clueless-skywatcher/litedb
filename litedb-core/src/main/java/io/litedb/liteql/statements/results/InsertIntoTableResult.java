package io.litedb.liteql.statements.results;

import java.util.List;

import io.litedb.tuples.LTRow;
import lombok.Getter;
import lombok.Setter;

public class InsertIntoTableResult implements LiteQLResult {
    private @Getter @Setter long timeTaken;
    private @Getter String tableName;

    public InsertIntoTableResult(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<LTRow> getRows() {
        return List.of();
    }

    public String toString() {
        return String.format("INSERT INTO %s completed in %d ms", tableName, timeTaken);
    }
}
