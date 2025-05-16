package io.litedb.liteql.statements;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import io.litedb.LiteDB;
import io.litedb.liteql.LTPredicate;
import io.litedb.liteql.statements.results.SelectFromTableResult;
import io.litedb.planning.LTCartesianProductPlan;
import io.litedb.planning.LTDBPlan;
import io.litedb.planning.LTFilteringPlan;
import io.litedb.planning.LTFullTablePlan;
import io.litedb.planning.LTProjectionPlan;
import io.litedb.scanning.LTDBScan;
import io.litedb.tuples.LTRow;
import io.litedb.tuples.LTSchema;
import lombok.Getter;

public class LTSelectFromTableStatement implements LTStatement {

    private @Getter List<String> tableNames;
    private @Getter List<LTPredicate> predicates;
    private @Getter List<String> fields;

    private @Getter SelectFromTableResult result;

    public LTSelectFromTableStatement(List<String> tableNames, List<String> fields,
            List<LTPredicate> predicates) {
        this.tableNames = tableNames;
        this.fields = fields;
        this.predicates = predicates;
    }

    public LTSelectFromTableStatement(List<String> tableNames, List<String> fields) {
        this.tableNames = tableNames;
        this.fields = fields;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            LTDBPlan plan = new LTFullTablePlan(tableNames.get(0), db, true);
            LTSchema schema = db.getOverseer().getTableSchema(tableNames.get(0));

            for (int i = 1; i < tableNames.size(); i++) {
                LTDBPlan fullTablePlan = new LTFullTablePlan(tableNames.get(i), db, true);
                plan = new LTCartesianProductPlan(plan, fullTablePlan);
                schema = schema.appendSchema(db.getOverseer().getTableSchema(tableNames.get(i)));
            }

            if (predicates != null) {
                if (predicates.size() > 0) {
                    plan = new LTFilteringPlan(plan, predicates);
                }
            }

            if (fields.size() == 0) {
                fields = new ArrayList<>(schema.getFields());
            }

            plan = new LTProjectionPlan(plan, fields);
            LTDBScan scan = plan.start();

            StringJoiner joiner = new StringJoiner(", ");
            for (String tableName : tableNames) {
                joiner.add(tableName);
            }

            this.result = new SelectFromTableResult(tableNames.toString(), fields);

            LTRow currentRow;

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
    public void execute(LiteDB db, LTDBPlan plan) {
        try {
            LTDBScan scan = plan.start();
            List<String> fields = new ArrayList<>(scan.getTableSchema().getFields());
            StringJoiner joiner = new StringJoiner(", ");
            for (String tableName : tableNames) {
                joiner.add(tableName);
            }

            this.result = new SelectFromTableResult(tableNames.toString(), fields);

            LTRow currentRow;

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
