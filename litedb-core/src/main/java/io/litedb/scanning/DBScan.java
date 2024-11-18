package io.litedb.scanning;

import java.io.IOException;

import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;

public interface DBScan {
    public void begin() throws IOException;
    public boolean next() throws IOException; 
    public LiteRow readRow();
    public TableSchema getTableSchema();
}
