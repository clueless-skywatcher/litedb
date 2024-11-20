package io.litedb.scanning;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.litedb.liteql.QueryPredicate;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.data.TupleData;

public interface WritableScan extends DBScan {
    public void insert(LiteRow row) throws IOException;
    public void update(Map<String, TupleData<?>> data) throws IOException;
    public void update(Map<String, TupleData<?>> data, List<QueryPredicate> predicate) throws IOException;
    public void delete() throws IOException;
    public void delete(List<QueryPredicate> predicates) throws IOException;
}
