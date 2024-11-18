package io.litedb.liteql.statements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.litedb.LiteDB;
import io.litedb.liteql.QueryPredicate;
import io.litedb.liteql.statements.results.SelectFromTableResult;
import io.litedb.planning.DBPlan;
import io.litedb.planning.FilteringPlan;
import io.litedb.planning.FullTablePlan;
import io.litedb.planning.ProjectionPlan;
import io.litedb.scanning.DBScan;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import lombok.Getter;

public class SelectFromTableStatement implements LiteQLStatement {
    private @Getter String tableName;
    private @Getter List<String> fields;
    private @Getter SelectFromTableResult result = null;
    private @Getter List<QueryPredicate> predicates;

    public SelectFromTableStatement(String tableName, List<String> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }

    public SelectFromTableStatement(String tableName, List<String> fields, List<QueryPredicate> predicates) {
        this.tableName = tableName;
        this.fields = fields;
        this.predicates = predicates;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            long timeTaken = System.currentTimeMillis();
            TableSchema schema = db.getOverseer().getTableSchema(tableName);

            DBPlan plan = new FullTablePlan(tableName, db.getStorageEngine(), db.getOverseer());
            
            if (predicates != null) {
                if (predicates.size() > 0) {
                    plan = new FilteringPlan(plan, predicates);
                }
            }
            
            if (fields.size() == 0) {
                fields = new ArrayList<>(schema.getFields());
            }
            plan = new ProjectionPlan(plan, fields);
            DBScan scan = plan.start();

            SelectFromTableResult result = new SelectFromTableResult(tableName, fields);

            LiteRow currentRow;
            scan.begin();

            while ((currentRow = scan.readRow()) != null) {
                result.addRow(currentRow);
                scan.next();
            }
            
            result.setTimeTaken(System.currentTimeMillis() - timeTaken);
            this.result = result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }
}
