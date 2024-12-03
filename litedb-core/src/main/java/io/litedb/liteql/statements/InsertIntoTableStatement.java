package io.litedb.liteql.statements;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.litedb.LiteDB;
import io.litedb.liteql.statements.results.InsertIntoTableResult;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.planning.DBPlan;
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
import lombok.Getter;

public class InsertIntoTableStatement implements LiteQLStatement {
    private @Getter LiteQLResult result;
    private @Getter List<String> fields;
    private @Getter String tableName;
    private @Getter List<String> row;

    public InsertIntoTableStatement(String tableName, List<String> fields, List<String> row) {
        this.tableName = tableName;
        this.row = row;
        this.fields = fields;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            TableSchema schema = db.getOverseer().getTableSchema(tableName);
            Map<String, TupleData<?>> data = new LinkedHashMap<>();
            for (int f = 0; f < row.size(); f++) {
                String fieldName = fields.get(f);
                TupleDatumInfo info = schema.getFieldInfo(fieldName);
                if (info instanceof IntegerInfo) {
                    data.put(fieldName, new IntegerData(Integer.parseInt(row.get(f))));
                } else if (info instanceof VarcharInfo) {
                    data.put(fieldName, new VarcharData(row.get(f), ((VarcharInfo) info).getMaxSize()));
                }
            }
            WritableScan scan = new FullTableScan(tableName, db.getStorageEngine(), db.getOverseer(),
                    db.getBufferManager());
            scan.begin();
            scan.insert(new LiteRow(data));
            this.result = new InsertIntoTableResult(tableName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert data: " + e.getMessage());
        }
    }

    @Override
    public boolean isDDL() {
        return false;
    }

    @Override
    public boolean isDML() {
        return true;
    }

    @Override
    public boolean isDQL() {
        return false;
    }

    @Override
    public void execute(LiteDB db, DBPlan plan) {
        try {
            TableSchema schema = db.getOverseer().getTableSchema(tableName);
            Map<String, TupleData<?>> data = new LinkedHashMap<>();
            for (int f = 0; f < row.size(); f++) {
                String fieldName = fields.get(f);
                TupleDatumInfo info = schema.getFieldInfo(fieldName);
                if (info instanceof IntegerInfo) {
                    data.put(fieldName, new IntegerData(Integer.parseInt(row.get(f))));
                } else if (info instanceof VarcharInfo) {
                    data.put(fieldName, new VarcharData(row.get(f), ((VarcharInfo) info).getMaxSize()));
                }
            }
            WritableScan scan = (WritableScan) plan.start();
            scan.begin();
            scan.insert(new LiteRow(data));
            this.result = new InsertIntoTableResult(tableName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert data: " + e.getMessage());
        }
    }
}
