package io.litedb.liteql.statements;

import io.litedb.LiteDB;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.planning.DBPlan;

public interface LiteQLStatement {
    public void execute(LiteDB db);
    public void execute(LiteDB db, DBPlan plan);
    public LiteQLResult getResult();
    public boolean isDDL();
    public boolean isDML();
    public boolean isDQL();
}
