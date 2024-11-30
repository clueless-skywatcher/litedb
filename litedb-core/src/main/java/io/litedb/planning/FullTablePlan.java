package io.litedb.planning;

import java.io.IOException;

import io.litedb.LiteDB;
import io.litedb.buffer.LiteBufferManager;
import io.litedb.filesystem.LiteStorageEngine;
import io.litedb.metadata.MetadataOverseer;
import io.litedb.scanning.DBScan;
import io.litedb.scanning.FullTableScan;

import lombok.Getter;

public class FullTablePlan implements DBPlan {
    private @Getter String tableName;
    private LiteStorageEngine storageEngine;
    private MetadataOverseer overseer;
    private boolean scanMode;
    private LiteBufferManager bufferManager;
    
    public FullTablePlan(String tableName, LiteDB db, boolean scanMode) {
        this(tableName, db.getStorageEngine(), db.getOverseer(), db.getBufferManager(), scanMode);
    }
    
    public FullTablePlan(String tableName, 
        LiteStorageEngine storageEngine, 
        MetadataOverseer overseer, 
        LiteBufferManager bufferManager,
        boolean scanMode
    ) {
        this.tableName = tableName;
        this.overseer = overseer;
        this.storageEngine = storageEngine;
        this.bufferManager = bufferManager;
        this.scanMode = scanMode;
    }

    @Override
    public DBScan start() throws IOException {
        return new FullTableScan(tableName, storageEngine, overseer, bufferManager, scanMode);
    }

    public String toString() {
        return String.format("FullTableScan on %s", tableName);
    }
}
