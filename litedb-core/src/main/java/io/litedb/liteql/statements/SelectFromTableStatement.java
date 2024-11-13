package io.litedb.liteql.statements;

import java.util.List;

import io.litedb.liteql.statements.results.LiteQLResult;
import lombok.Getter;

public class SelectFromTableStatement implements LiteQLStatement {
    private @Getter String tableName;
    private @Getter List<String> fields;

    public SelectFromTableStatement(String tableName, List<String> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }

    @Override
    public void execute() {

    }

    @Override
    public LiteQLResult getResult() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResult'");
    }
}
