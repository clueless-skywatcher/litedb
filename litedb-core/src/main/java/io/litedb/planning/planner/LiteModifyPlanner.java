package io.litedb.planning.planner;

import io.litedb.LiteDB;
import io.litedb.liteql.statements.DeleteFromTableStatement;
import io.litedb.liteql.statements.InsertIntoTableStatement;
import io.litedb.liteql.statements.UpdateTableStatement;
import io.litedb.planning.DBPlan;
import io.litedb.planning.FullTablePlan;

public class LiteModifyPlanner implements AbstractModifyPlanner {
    private LiteDB db;

    public LiteModifyPlanner(LiteDB db) {
        this.db = db;
    }

    @Override
    public DBPlan createInsertIntoTablePlan(InsertIntoTableStatement stmt) {
        return new FullTablePlan(
            stmt.getTableName(),
            db.getStorageEngine(),
            db.getOverseer(),
            db.getBufferManager(),
            false
        );
    }

    @Override
    public DBPlan createUpdateTablePlan(UpdateTableStatement stmt) {
        return new FullTablePlan(
            stmt.getTableName(),
            db.getStorageEngine(),
            db.getOverseer(),
            db.getBufferManager(),
            true
        );
    }

    @Override
    public DBPlan createDeleteFromTablePlan(DeleteFromTableStatement stmt) {
        return new FullTablePlan(
            stmt.getTableName(), 
            db.getStorageEngine(), 
            db.getOverseer(),
            db.getBufferManager(),
            true
        );
    }

}
