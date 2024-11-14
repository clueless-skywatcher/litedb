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
    
    public FullTablePlan(String tableName, LiteStorageEngine storageEngine, MetadataOverseer overseer) {
        this.tableName = tableName;
        this.overseer = overseer;
        this.storageEngine = storageEngine;
    }

    @Override
    public DBScan start() throws IOException {
        return new FullTableScan(tableName, storageEngine, overseer);
    }

    public String toString() {
        return String.format("FullTableScan on %s", tableName);
    }
}
