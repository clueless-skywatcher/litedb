package io.litedb.planning.planner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.litedb.LiteDB;
import io.litedb.liteql.QueryPredicate;
import io.litedb.liteql.statements.SelectFromTableStatement;
import io.litedb.planning.CartesianProductPlan;
import io.litedb.planning.DBPlan;
import io.litedb.planning.FilteringPlan;
import io.litedb.planning.FullTablePlan;
import io.litedb.planning.ProjectionPlan;
import io.litedb.tuples.TableSchema;

public class LiteQueryPlanner implements AbstractQueryPlanner {
    private LiteDB db;

    public LiteQueryPlanner(LiteDB db) {
        this.db = db;
    }

    @Override
    public DBPlan createPlan(SelectFromTableStatement stmt) throws IOException {
        List<String> tableNames = stmt.getTableNames();
        List<QueryPredicate> predicates = stmt.getPredicates();
        List<String> fields = stmt.getFields();

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
        return plan;
    }

}
