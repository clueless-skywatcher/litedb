package io.litedb.planning;

import java.io.IOException;

import io.litedb.filesystem.LiteStorageEngine;
import io.litedb.metadata.MetadataOverseer;
import io.litedb.scanning.DBScan;
import io.litedb.scanning.FullTableScan;

public class FullTablePlan implements DBPlan {
    private String tableName;
    private LiteStorageEngine storageEngine;
    private MetadataOverseer overseer;
    private boolean scanMode;
    
    public FullTablePlan(String tableName, LiteStorageEngine storageEngine, MetadataOverseer overseer, boolean scanMode) {
        this.tableName = tableName;
        this.overseer = overseer;
        this.storageEngine = storageEngine;
        this.scanMode = scanMode;
    }

    @Override
    public DBScan start() throws IOException {
        return new FullTableScan(tableName, storageEngine, overseer, scanMode);
    }

    public String toString() {
        return String.format("FullTableScan on %s", tableName);
    }
}
