package io.litedb.liteql.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import io.litedb.LiteDB;
import io.litedb.liteql.QueryPredicate;
import io.litedb.liteql.statements.results.SelectFromTableResult;
import io.litedb.planning.CartesianProductPlan;
import io.litedb.planning.DBPlan;
import io.litedb.planning.FilteringPlan;
import io.litedb.planning.FullTablePlan;
import io.litedb.planning.ProjectionPlan;
import io.litedb.scanning.DBScan;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import lombok.Getter;

public class SelectFromTableStatement implements LiteQLStatement {

    private @Getter List<String> tableNames;
    private @Getter List<QueryPredicate> predicates;
    private @Getter List<String> fields;

    private @Getter SelectFromTableResult result;

    public SelectFromTableStatement(List<String> tableNames, List<String> fields,
            List<QueryPredicate> predicates) {
        this.tableNames = tableNames;
        this.fields = fields;
        this.predicates = predicates;
    }

    public SelectFromTableStatement(List<String> tableNames, List<String> fields) {
        this.tableNames = tableNames;
        this.fields = fields;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            DBPlan plan = new FullTablePlan(tableNames.get(0), db, true);
            TableSchema schema = db.getOverseer().getTableSchema(tableNames.get(0));

            for (int i = 1; i < tableNames.size(); i++) {
                DBPlan fullTablePlan = new FullTablePlan(tableNames.get(i), db, true);
                plan = new CartesianProductPlan(plan, fullTablePlan);
                schema = schema.appendSchema(db.getOverseer().getTableSchema(tableNames.get(i)));
            }

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

            StringJoiner joiner = new StringJoiner(", ");
            for (String tableName : tableNames) {
                joiner.add(tableName);
            }

            this.result = new SelectFromTableResult(tableNames.toString(), fields);

            LiteRow currentRow;

            scan.begin();

            while ((currentRow = scan.readRow()) != null) {
                result.addRow(currentRow);
                scan.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }

    @Override
    public boolean isDDL() {
        return false;
    }

    @Override
    public boolean isDML() {
        return false;
    }

    @Override
    public boolean isDQL() {
        return true;
    }

    @Override
    public void execute(LiteDB db, DBPlan plan) {
        try {
            DBScan scan = plan.start();
            List<String> fields = new ArrayList<>(scan.getTableSchema().getFields());
            StringJoiner joiner = new StringJoiner(", ");
            for (String tableName : tableNames) {
                joiner.add(tableName);
            }

            this.result = new SelectFromTableResult(tableNames.toString(), fields);

            LiteRow currentRow;

            scan.begin();

            while ((currentRow = scan.readRow()) != null) {
                result.addRow(currentRow);
                scan.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
