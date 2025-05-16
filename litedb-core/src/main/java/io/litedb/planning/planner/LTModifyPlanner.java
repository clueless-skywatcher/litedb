package io.litedb.planning.planner;

import io.litedb.LiteDB;
import io.litedb.liteql.statements.LTDeleteFromTableStatement;
import io.litedb.liteql.statements.LTInsertIntoTableStatement;
import io.litedb.liteql.statements.LTUpdateTableStatement;
import io.litedb.planning.LTDBPlan;
import io.litedb.planning.LTFullTablePlan;

public class LTModifyPlanner implements LTAbstractModifyPlanner {
    private LiteDB db;

    public LTModifyPlanner(LiteDB db) {
        this.db = db;
    }

    @Override
    public LTDBPlan createInsertIntoTablePlan(LTInsertIntoTableStatement stmt) {
        return new LTFullTablePlan(
            stmt.getTableName(),
            db.getStorageEngine(),
            db.getOverseer(),
            db.getBufferManager(),
            false
        );
    }

    @Override
    public LTDBPlan createUpdateTablePlan(LTUpdateTableStatement stmt) {
        return new LTFullTablePlan(
            stmt.getTableName(),
            db.getStorageEngine(),
            db.getOverseer(),
            db.getBufferManager(),
            true
        );
    }

    @Override
    public LTDBPlan createDeleteFromTablePlan(LTDeleteFromTableStatement stmt) {
        return new LTFullTablePlan(
            stmt.getTableName(), 
            db.getStorageEngine(), 
            db.getOverseer(),
            db.getBufferManager(),
            true
        );
    }

}
