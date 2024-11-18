package io.litedb.planning;

import java.io.IOException;
import java.util.List;

import io.litedb.liteql.QueryPredicate;
import io.litedb.scanning.DBScan;
import io.litedb.scanning.FilteringScan;

public class FilteringPlan implements DBPlan {
    private DBPlan underlyingPlan;
    private List<QueryPredicate> predicates;

    public FilteringPlan(DBPlan plan, List<QueryPredicate> predicates) {
        this.underlyingPlan = plan;
        this.predicates = predicates;
    }

    @Override
    public DBScan start() throws IOException {
        return new FilteringScan(underlyingPlan.start(), predicates);
    }    

    public String toString() {
        return String.format("FilteringScan on (%s) with filter %s", underlyingPlan.toString(), predicates.toString());
    }
}
