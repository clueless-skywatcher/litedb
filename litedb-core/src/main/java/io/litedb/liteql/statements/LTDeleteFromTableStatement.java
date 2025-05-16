package io.litedb.liteql.statements;

import java.util.List;

import lombok.Getter;

import io.litedb.LiteDB;
import io.litedb.liteql.LTPredicate;
import io.litedb.liteql.statements.results.DeleteFromTableResult;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.planning.LTDBPlan;
import io.litedb.planning.LTFullTablePlan;
import io.litedb.scanning.LTWritableScan;

public class LTDeleteFromTableStatement implements LTStatement {
    private @Getter LiteQLResult result;
    private @Getter String tableName;
    private @Getter List<LTPredicate> predicates;

    public LTDeleteFromTableStatement(String tableName) {
        this.tableName = tableName;
    }

    public LTDeleteFromTableStatement(String tableName, List<LTPredicate> predicates) {
        this.tableName = tableName;
        this.predicates = predicates;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            LTDBPlan plan = new LTFullTablePlan(
                tableName, 
                db.getStorageEngine(), 
                db.getOverseer(),
                db.getBufferManager(),
                true
            );
            LTWritableScan scan = (LTWritableScan) plan.start();

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
    public void execute(LiteDB db, LTDBPlan plan) {
        try {
            LTWritableScan scan = (LTWritableScan) plan.start();

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
