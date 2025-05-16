package io.litedb.scanning;

import java.io.IOException;
import java.util.List;

import io.litedb.liteql.LTPredicate;
import io.litedb.tuples.LTRow;
import io.litedb.tuples.LTSchema;

public class LTFilteringScan implements LTDBScan {
    private LTDBScan scan;
    private List<LTPredicate> predicates;

    public LTFilteringScan(LTDBScan scan, List<LTPredicate> predicates) {
        this.scan = scan;
        this.predicates = predicates;
    }

    @Override
    public void begin() throws IOException {
        scan.begin();
        while (scan.readRow() != null && !currentRowSatisfiesPredicates()) {
            scan.next();
        }
    }

    @Override
    public boolean next() throws IOException {
        while (true) {
            if (scan.next()) {
                boolean allSatisfied = currentRowSatisfiesPredicates();
                if (allSatisfied) {
                    return true;
                } else {
                    continue;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public LTRow readRow() {
        return scan.readRow();
    }

    @Override
    public LTSchema getTableSchema() {
        return scan.getTableSchema();
    }

    private boolean currentRowSatisfiesPredicates() {
        boolean allSatisfied = true;
        for (LTPredicate predicate: predicates) {
            allSatisfied = allSatisfied && predicate.rowSatisfies(scan.readRow(), getTableSchema());
        }
        return allSatisfied;
    }
}
