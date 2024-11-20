package io.litedb.liteql.statements;

import java.util.List;

import lombok.Getter;

import io.litedb.LiteDB;
import io.litedb.liteql.QueryPredicate;
import io.litedb.liteql.statements.results.DeleteFromTableResult;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.liteql.statements.results.UpdateTableResult;
import io.litedb.planning.DBPlan;
import io.litedb.planning.FullTablePlan;
import io.litedb.scanning.WritableScan;

public class DeleteFromTableStatement implements LiteQLStatement {
    private @Getter LiteQLResult result;
    private String tableName;
    private List<QueryPredicate> predicates;

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
            DBPlan plan = new FullTablePlan(tableName, db.getStorageEngine(), db.getOverseer());
            WritableScan scan = (WritableScan) plan.start();

            if (predicates != null) {
                if (predicates.size() > 0) {
                    scan.delete(predicates);
                }
            } else {
                scan.delete();
            }

            this.result = new DeleteFromTableResult(tableName);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
