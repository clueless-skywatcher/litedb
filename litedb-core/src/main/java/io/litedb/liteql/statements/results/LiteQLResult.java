package io.litedb.liteql.statements.results;

import java.util.List;

import io.litedb.tuples.LTRow;

public interface LiteQLResult {
    public List<LTRow> getRows();
    public long getTimeTaken();
    public void setTimeTaken(long timeTaken);
}
