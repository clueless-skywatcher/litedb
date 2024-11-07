package io.litedb.scanning;

import java.io.IOException;

import io.litedb.tuples.LiteRow;

public interface DBScan {
    public void begin() throws IOException;
    public boolean next() throws IOException; 
    public LiteRow readRow();
}
