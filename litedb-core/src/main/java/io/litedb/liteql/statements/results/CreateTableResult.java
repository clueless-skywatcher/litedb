package io.litedb.liteql.statements.results;

import java.util.List;

import io.litedb.tuples.LiteRow;
import lombok.Getter;
import lombok.Setter;

public class CreateTableResult implements LiteQLResult {
    private @Getter @Setter long timeTaken;
    private String tableName;

    public CreateTableResult(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public List<LiteRow> getRows() {
        return List.of();
    }    

    public String toString() {
        return String.format("CREATE TABLE %s completed in %d ms", tableName, timeTaken);
    }
}
