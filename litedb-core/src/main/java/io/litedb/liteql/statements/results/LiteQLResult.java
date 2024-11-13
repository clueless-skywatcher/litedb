package io.litedb.liteql.statements.results;

import java.util.List;

import io.litedb.tuples.LiteRow;

public interface LiteQLResult {
    public List<LiteRow> getRows();
}
