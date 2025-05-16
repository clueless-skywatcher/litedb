package io.litedb.planning.planner;

import io.litedb.liteql.statements.LTDeleteFromTableStatement;
import io.litedb.liteql.statements.LTInsertIntoTableStatement;
import io.litedb.liteql.statements.LTStatement;
import io.litedb.liteql.statements.LTUpdateTableStatement;
import io.litedb.planning.LTDBPlan;

public interface LTAbstractModifyPlanner {
    default LTDBPlan createPlan(LTStatement stmt) {
        if (stmt instanceof LTInsertIntoTableStatement) {
            return createInsertIntoTablePlan((LTInsertIntoTableStatement) stmt);
        } else if (stmt instanceof LTUpdateTableStatement) {
            return createUpdateTablePlan((LTUpdateTableStatement) stmt);
        } else {
            return createDeleteFromTablePlan((LTDeleteFromTableStatement) stmt);
        }
    }

    public LTDBPlan createInsertIntoTablePlan(LTInsertIntoTableStatement stmt);
    public LTDBPlan createUpdateTablePlan(LTUpdateTableStatement stmt);
    public LTDBPlan createDeleteFromTablePlan(LTDeleteFromTableStatement stmt);
}
