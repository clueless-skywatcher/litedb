package io.litedb.scanning;

import java.io.IOException;

import io.litedb.tuples.LiteRow;

public interface WritableScan extends DBScan {
    public void insert(LiteRow row) throws IOException;
}
