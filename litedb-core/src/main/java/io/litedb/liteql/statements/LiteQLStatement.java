package io.litedb.liteql.statements;

import io.litedb.liteql.statements.results.LiteQLResult;

public interface LiteQLStatement {
    public void execute();
    public LiteQLResult getResult();
}
