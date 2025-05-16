package io.litedb.scanning;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.litedb.liteql.LTPredicate;
import io.litedb.tuples.LTRow;
import io.litedb.tuples.data.LTTupleData;

public interface LTWritableScan extends LTDBScan {
    public void insert(LTRow row) throws IOException;
    public int update(Map<String, LTTupleData<?>> data) throws IOException;
    public int update(Map<String, LTTupleData<?>> data, List<LTPredicate> predicate) throws IOException;
    public int delete() throws IOException;
    public int delete(List<LTPredicate> predicates) throws IOException;
}
