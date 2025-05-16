package io.litedb.planning;

import java.io.IOException;

import io.litedb.LiteDB;
import io.litedb.buffer.LTBufferManager;
import io.litedb.filesystem.LTStorageEngine;
import io.litedb.metadata.LTMetadataOverseer;
import io.litedb.scanning.LTDBScan;
import io.litedb.scanning.LTFullTableScan;

import lombok.Getter;

public class LTFullTablePlan implements LTDBPlan {
    private @Getter String tableName;
    private LTStorageEngine storageEngine;
    private LTMetadataOverseer overseer;
    private boolean scanMode;
    private LTBufferManager bufferManager;
    
    public LTFullTablePlan(String tableName, LiteDB db, boolean scanMode) {
        this(tableName, db.getStorageEngine(), db.getOverseer(), db.getBufferManager(), scanMode);
    }
    
    public LTFullTablePlan(String tableName, 
        LTStorageEngine storageEngine, 
        LTMetadataOverseer overseer, 
        LTBufferManager bufferManager,
        boolean scanMode
    ) {
        this.tableName = tableName;
        this.overseer = overseer;
        this.storageEngine = storageEngine;
        this.bufferManager = bufferManager;
        this.scanMode = scanMode;
    }

    @Override
    public LTDBScan start() throws IOException {
        return new LTFullTableScan(tableName, storageEngine, overseer, bufferManager, scanMode);
    }

    public String toString() {
        return String.format("FullTableScan on %s", tableName);
    }
}
