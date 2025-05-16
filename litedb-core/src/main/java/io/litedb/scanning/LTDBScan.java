package io.litedb.scanning;

import java.io.IOException;

import io.litedb.tuples.LTRow;
import io.litedb.tuples.LTSchema;

public interface LTDBScan {
    public void begin() throws IOException;
    public boolean next() throws IOException; 
    public LTRow readRow();
    public LTSchema getTableSchema();
}
