package io.litedb.planning.planner;

import java.io.IOException;

import io.litedb.liteql.statements.LTSelectFromTableStatement;
import io.litedb.planning.LTDBPlan;

public interface LTAbstractQueryPlanner {
    public LTDBPlan createPlan(LTSelectFromTableStatement stmt) throws IOException;
}
