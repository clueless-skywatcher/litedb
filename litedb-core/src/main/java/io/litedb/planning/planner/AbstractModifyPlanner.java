package io.litedb.planning.planner;

import io.litedb.liteql.statements.DeleteFromTableStatement;
import io.litedb.liteql.statements.InsertIntoTableStatement;
import io.litedb.liteql.statements.LiteQLStatement;
import io.litedb.liteql.statements.UpdateTableStatement;
import io.litedb.planning.DBPlan;

public interface AbstractModifyPlanner {
    default DBPlan createPlan(LiteQLStatement stmt) {
        if (stmt instanceof InsertIntoTableStatement) {
            return createInsertIntoTablePlan((InsertIntoTableStatement) stmt);
        } else if (stmt instanceof UpdateTableStatement) {
            return createUpdateTablePlan((UpdateTableStatement) stmt);
        } else {
            return createDeleteFromTablePlan((DeleteFromTableStatement) stmt);
        }
    }

    public DBPlan createInsertIntoTablePlan(InsertIntoTableStatement stmt);
    public DBPlan createUpdateTablePlan(UpdateTableStatement stmt);
    public DBPlan createDeleteFromTablePlan(DeleteFromTableStatement stmt);
}
