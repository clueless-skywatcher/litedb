package io.litedb.liteql.statements;

import io.litedb.LiteDB;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.planning.LTDBPlan;

public interface LTStatement {
    public void execute(LiteDB db);
    public void execute(LiteDB db, LTDBPlan plan);
    public LiteQLResult getResult();
    public boolean isDDL();
    public boolean isDML();
    public boolean isDQL();
}
