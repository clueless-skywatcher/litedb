package io.litedb.scanning;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.litedb.liteql.QueryPredicate;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.TupleData;

public class FilteringScan implements DBScan {
    private DBScan scan;
    private List<QueryPredicate> predicates;

    public FilteringScan(DBScan scan, List<QueryPredicate> predicates) {
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
    public LiteRow readRow() {
        return scan.readRow();
    }

    @Override
    public TableSchema getTableSchema() {
        return scan.getTableSchema();
    }

    private boolean currentRowSatisfiesPredicates() {
        boolean allSatisfied = true;
        for (QueryPredicate predicate: predicates) {
            allSatisfied = allSatisfied && predicate.rowSatisfies(scan.readRow(), getTableSchema());
        }
        return allSatisfied;
    }
}
