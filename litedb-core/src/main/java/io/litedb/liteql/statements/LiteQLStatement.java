package io.litedb.liteql.statements;

import io.litedb.LiteDB;
import io.litedb.liteql.statements.results.LiteQLResult;

public interface LiteQLStatement {
    public void execute(LiteDB db);
    public LiteQLResult getResult();
}
