package io.litedb;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.litedb.buffer.LTBufferManager;
import io.litedb.filesystem.LTStorageEngine;
import io.litedb.metadata.LTMetadataOverseer;
import io.litedb.planning.planner.LTAbstractModifyPlanner;
import io.litedb.planning.planner.LTAbstractQueryPlanner;
import io.litedb.planning.planner.LTModifyPlanner;
import io.litedb.planning.planner.LTQueryPlanner;
import io.litedb.scanning.LTDBScan;
import io.litedb.scanning.LTFullTableScan;
import io.litedb.scanning.LTProjectionScan;
import io.litedb.scanning.LTWritableScan;
import io.litedb.tuples.LTRow;
import io.litedb.tuples.LTSchema;
import io.litedb.tuples.data.LTTupleData;
import io.litedb.tuples.data.info.LTTupleDatumInfo;
import lombok.Getter;

public class LiteDB {
    private @Getter LTStorageEngine storageEngine;
    private @Getter LTMetadataOverseer overseer;
    private @Getter LTBufferManager bufferManager;
    private @Getter LTAbstractQueryPlanner queryPlanner;
    private @Getter LTAbstractModifyPlanner modifyPlanner;

    public LiteDB(String dbDirectory) {
        this.storageEngine = new LTStorageEngine(dbDirectory);
        this.bufferManager = new LTBufferManager(storageEngine);
        this.overseer = new LTMetadataOverseer(storageEngine, bufferManager);
        this.queryPlanner = new LTQueryPlanner(this);
        this.modifyPlanner = new LTModifyPlanner(this);
    }
    
    public void createTable(String tableName, Map<String, LTTupleDatumInfo> fields) {
        this.overseer.addTable(tableName, new LTSchema(fields));
        this.storageEngine.getFile(tableName + ".lt");
    }

    public void scanTable(String tableName) {
        try {
            LTDBScan scan = new LTFullTableScan(tableName, storageEngine, overseer, bufferManager);
            scan.begin();
            LTRow currentRow;
            int rowCount = 0;
            while ((currentRow = scan.readRow()) != null) {
                System.out.println(currentRow);
                if (rowCount == 140) {
                    System.out.println();
                }
                rowCount++;
                scan.next();
            }
            System.out.println(String.format("%d rows fetched", rowCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scanTable(String tableName, List<String> fields) {
        try {
            LTProjectionScan newScan = new LTProjectionScan(new LTFullTableScan(tableName, storageEngine, overseer, bufferManager), fields);
            newScan.begin();
            LTRow currentRow;
            int rowCount = 0;
            while ((currentRow = newScan.readRow()) != null) {
                System.out.println(currentRow);
                rowCount++;
                newScan.next();
            }
            System.out.println(String.format("%d rows fetched", rowCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertValues(String tableName, Map<String, LTTupleData<?>> values) {
        try {
            LTWritableScan scan = new LTFullTableScan(tableName, storageEngine, overseer, bufferManager);
            scan.begin();
            scan.insert(new LTRow(values));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
