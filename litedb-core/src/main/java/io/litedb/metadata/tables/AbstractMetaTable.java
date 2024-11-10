package io.litedb.metadata.tables;

import io.litedb.tuples.TableSchema;

public interface AbstractMetaTable {
    public TableSchema getSchema();
    public String tableName();
}
