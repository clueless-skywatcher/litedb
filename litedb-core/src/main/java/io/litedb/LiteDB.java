package io.litedb;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.litedb.buffer.LiteBufferManager;
import io.litedb.filesystem.LiteStorageEngine;
import io.litedb.metadata.MetadataOverseer;
import io.litedb.planning.planner.AbstractModifyPlanner;
import io.litedb.planning.planner.AbstractQueryPlanner;
import io.litedb.planning.planner.LiteModifyPlanner;
import io.litedb.planning.planner.LiteQueryPlanner;
import io.litedb.scanning.DBScan;
import io.litedb.scanning.FullTableScan;
import io.litedb.scanning.ProjectionScan;
import io.litedb.scanning.WritableScan;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.TupleData;
import io.litedb.tuples.data.info.TupleDatumInfo;
import lombok.Getter;

public class LiteDB {
    private @Getter LiteStorageEngine storageEngine;
    private @Getter MetadataOverseer overseer;
    private @Getter LiteBufferManager bufferManager;
    private @Getter AbstractQueryPlanner queryPlanner;
    private @Getter AbstractModifyPlanner modifyPlanner;

    public LiteDB(String dbDirectory) {
        this.storageEngine = new LiteStorageEngine(dbDirectory);
        this.bufferManager = new LiteBufferManager(storageEngine);
        this.overseer = new MetadataOverseer(storageEngine, bufferManager);
        this.queryPlanner = new LiteQueryPlanner(this);
        this.modifyPlanner = new LiteModifyPlanner(this);
    }
    
    public void createTable(String tableName, Map<String, TupleDatumInfo> fields) {
        this.overseer.addTable(tableName, new TableSchema(fields));
        this.storageEngine.getFile(tableName + ".lt");
    }

    public void scanTable(String tableName) {
        try {
            DBScan scan = new FullTableScan(tableName, storageEngine, overseer, bufferManager);
            scan.begin();
            LiteRow currentRow;
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
            ProjectionScan newScan = new ProjectionScan(new FullTableScan(tableName, storageEngine, overseer, bufferManager), fields);
            newScan.begin();
            LiteRow currentRow;
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

    public void insertValues(String tableName, Map<String, TupleData<?>> values) {
        try {
            WritableScan scan = new FullTableScan(tableName, storageEngine, overseer, bufferManager);
            scan.begin();
            scan.insert(new LiteRow(values));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
