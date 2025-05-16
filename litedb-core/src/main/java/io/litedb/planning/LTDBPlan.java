package io.litedb.planning;

import java.io.IOException;

import io.litedb.scanning.LTDBScan;

public interface LTDBPlan {
    public LTDBScan start() throws IOException;
}
