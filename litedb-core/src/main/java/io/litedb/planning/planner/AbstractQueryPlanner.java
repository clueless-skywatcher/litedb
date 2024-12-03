package io.litedb.planning.planner;

import java.io.IOException;

import io.litedb.liteql.statements.SelectFromTableStatement;
import io.litedb.planning.DBPlan;

public interface AbstractQueryPlanner {
    public DBPlan createPlan(SelectFromTableStatement stmt) throws IOException;
}
