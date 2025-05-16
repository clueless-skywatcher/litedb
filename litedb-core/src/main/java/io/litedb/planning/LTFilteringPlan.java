package io.litedb.planning;

import java.io.IOException;
import java.util.List;

import io.litedb.liteql.LTPredicate;
import io.litedb.scanning.LTDBScan;
import io.litedb.scanning.LTFilteringScan;

public class LTFilteringPlan implements LTDBPlan {
    private LTDBPlan underlyingPlan;
    private List<LTPredicate> predicates;

    public LTFilteringPlan(LTDBPlan plan, List<LTPredicate> predicates) {
        this.underlyingPlan = plan;
        this.predicates = predicates;
    }

    @Override
    public LTDBScan start() throws IOException {
        return new LTFilteringScan(underlyingPlan.start(), predicates);
    }    

    public String toString() {
        return String.format("FilteringScan on (%s) with filter %s", underlyingPlan.toString(), predicates.toString());
    }
}
