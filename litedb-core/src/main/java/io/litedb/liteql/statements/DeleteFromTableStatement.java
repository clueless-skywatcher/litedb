package io.litedb.liteql.statements;

import java.util.List;

import lombok.Getter;

import io.litedb.LiteDB;
import io.litedb.liteql.QueryPredicate;
import io.litedb.liteql.statements.results.DeleteFromTableResult;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.planning.DBPlan;
import io.litedb.planning.FullTablePlan;
import io.litedb.scanning.WritableScan;

public class DeleteFromTableStatement implements LiteQLStatement {
    private @Getter LiteQLResult result;
    private @Getter String tableName;
    private @Getter List<QueryPredicate> predicates;

    public DeleteFromTableStatement(String tableName) {
        this.tableName = tableName;
    }

    public DeleteFromTableStatement(String tableName, List<QueryPredicate> predicates) {
        this.tableName = tableName;
        this.predicates = predicates;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            DBPlan plan = new FullTablePlan(
                tableName, 
                db.getStorageEngine(), 
                db.getOverseer(),
                db.getBufferManager(),
                true
            );
            WritableScan scan = (WritableScan) plan.start();

            int rowsDeleted = 0;

            if (predicates != null) {
                rowsDeleted = scan.delete(predicates);
            } else {
                rowsDeleted = scan.delete();
            }

            this.result = new DeleteFromTableResult(tableName, rowsDeleted);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean isDDL() {
        return false;
    }

    @Override
    public boolean isDML() {
        return true;
    }

    @Override
    public boolean isDQL() {
        return false;
    }

    @Override
    public void execute(LiteDB db, DBPlan plan) {
        try {
            WritableScan scan = (WritableScan) plan.start();

            int rowsDeleted = 0;

            if (predicates != null) {
                rowsDeleted = scan.delete(predicates);
            } else {
                rowsDeleted = scan.delete();
            }

            this.result = new DeleteFromTableResult(tableName, rowsDeleted);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
