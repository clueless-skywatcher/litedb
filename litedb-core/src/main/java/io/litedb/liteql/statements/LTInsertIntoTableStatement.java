package io.litedb.liteql.statements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.litedb.LiteDB;
import io.litedb.liteql.statements.results.InsertIntoTableResult;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.planning.LTDBPlan;
import io.litedb.scanning.LTFullTableScan;
import io.litedb.scanning.LTWritableScan;
import io.litedb.tuples.LTRow;
import io.litedb.tuples.LTSchema;
import io.litedb.tuples.data.LTIntegerData;
import io.litedb.tuples.data.LTTupleData;
import io.litedb.tuples.data.LTVarcharData;
import io.litedb.tuples.data.info.LTIntegerInfo;
import io.litedb.tuples.data.info.LTTupleDatumInfo;
import io.litedb.tuples.data.info.LTVarcharInfo;
import lombok.Getter;

public class LTInsertIntoTableStatement implements LTStatement {
    private @Getter LiteQLResult result;
    private @Getter List<String> fields;
    private @Getter String tableName;
    private @Getter List<String> row;

    public LTInsertIntoTableStatement(String tableName, List<String> fields, List<String> row) {
        this.tableName = tableName;
        this.row = row;
        this.fields = fields;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            LTSchema schema = db.getOverseer().getTableSchema(tableName);
            Map<String, LTTupleData<?>> data = new HashMap<>();
            for (int f = 0; f < row.size(); f++) {
                String fieldName = fields.get(f);
                LTTupleDatumInfo info = schema.getFieldInfo(fieldName);
                if (info instanceof LTIntegerInfo) {
                    data.put(fieldName, new LTIntegerData(Integer.parseInt(row.get(f))));
                } else if (info instanceof LTVarcharInfo) {
                    data.put(fieldName, new LTVarcharData(row.get(f), ((LTVarcharInfo) info).getMaxSize()));
                }
            }
            LTWritableScan scan = new LTFullTableScan(tableName, db.getStorageEngine(), db.getOverseer(),
                    db.getBufferManager());
            scan.begin();
            scan.insert(new LTRow(data));
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
    public void execute(LiteDB db, LTDBPlan plan) {
        try {
            LTSchema schema = db.getOverseer().getTableSchema(tableName);
            Map<String, LTTupleData<?>> data = new HashMap<>();
            for (int f = 0; f < row.size(); f++) {
                String fieldName = fields.get(f);
                LTTupleDatumInfo info = schema.getFieldInfo(fieldName);
                if (info instanceof LTIntegerInfo) {
                    data.put(fieldName, new LTIntegerData(Integer.parseInt(row.get(f))));
                } else if (info instanceof LTVarcharInfo) {
                    data.put(fieldName, new LTVarcharData(row.get(f), ((LTVarcharInfo) info).getMaxSize()));
                }
            }
            LTWritableScan scan = (LTWritableScan) plan.start();
            scan.begin();
            scan.insert(new LTRow(data));
            this.result = new InsertIntoTableResult(tableName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert data: " + e.getMessage());
        }
    }
}
