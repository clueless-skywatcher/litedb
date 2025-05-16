package io.litedb.planning.planner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.litedb.LiteDB;
import io.litedb.liteql.LTPredicate;
import io.litedb.liteql.statements.LTSelectFromTableStatement;
import io.litedb.planning.LTCartesianProductPlan;
import io.litedb.planning.LTDBPlan;
import io.litedb.planning.LTFilteringPlan;
import io.litedb.planning.LTFullTablePlan;
import io.litedb.planning.LTProjectionPlan;
import io.litedb.tuples.LTSchema;

public class LTQueryPlanner implements LTAbstractQueryPlanner {
    private LiteDB db;

    public LTQueryPlanner(LiteDB db) {
        this.db = db;
    }

    @Override
    public LTDBPlan createPlan(LTSelectFromTableStatement stmt) throws IOException {
        List<String> tableNames = stmt.getTableNames();
        List<LTPredicate> predicates = stmt.getPredicates();
        List<String> fields = stmt.getFields();

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
        return plan;
    }

}
