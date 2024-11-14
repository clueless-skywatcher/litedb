package io.litedb.planning;

import java.io.IOException;

import io.litedb.scanning.DBScan;

public interface DBPlan {
    public DBScan start() throws IOException;
}
