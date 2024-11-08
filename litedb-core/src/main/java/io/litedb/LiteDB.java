package io.litedb;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

import io.litedb.filesystem.LiteStorageEngine;
import io.litedb.metadata.MetadataOverseer;
import io.litedb.scanning.DBScan;
import io.litedb.scanning.FullTableScan;
import io.litedb.scanning.WritableScan;
import io.litedb.tuples.LiteRow;
import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.IntegerData;
import io.litedb.tuples.data.TupleData;
import io.litedb.tuples.data.VarcharData;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class LiteDB 
{
    private LiteStorageEngine storageEngine;
    private MetadataOverseer overseer;

    public LiteDB(String dbDirectory) {
        this.storageEngine = new LiteStorageEngine(dbDirectory);
        this.overseer = MetadataOverseer.getInstance(dbDirectory);
    }

    public void createTable(String tableName, Map<String, TupleDatumInfo> fields) {
        this.overseer.addTable(tableName, new TableSchema(fields));
        this.storageEngine.getFile(tableName + ".lt");
    }

    public void scanTable(String tableName) {
        try {
            DBScan scan = new FullTableScan(tableName, storageEngine, overseer);
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

    public void insertValues(String tableName, Map<String, TupleData<?>> values) {
        try {
            WritableScan scan = new FullTableScan(tableName, storageEngine, overseer);
            scan.begin();
            scan.insert(new LiteRow(values));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main( String[] args )
    {
        Map<String, TupleDatumInfo> fields = new TreeMap<>();
        fields.put("roll_number", new IntegerInfo());
        fields.put("name", new VarcharInfo(200));

        LiteDB db = new LiteDB(".litedb");
        db.createTable("students", fields);

        db.insertValues("students", Map.ofEntries(
            new AbstractMap.SimpleEntry<>("roll_number", new IntegerData(1)),
            new AbstractMap.SimpleEntry<>("name", new VarcharData("Somi", 200))
        ));
        db.insertValues("students", Map.ofEntries(
            new AbstractMap.SimpleEntry<>("roll_number", new IntegerData(2)),
            new AbstractMap.SimpleEntry<>("name", new VarcharData("Epsi", 200))
        ));
        db.insertValues("students", Map.ofEntries(
            new AbstractMap.SimpleEntry<>("roll_number", new IntegerData(3)),
            new AbstractMap.SimpleEntry<>("name", new VarcharData("Shady", 200))
        ));

        db.scanTable("students");
    }
}
