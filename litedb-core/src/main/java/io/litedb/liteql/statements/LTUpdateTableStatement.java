package io.litedb.liteql.statements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.litedb.LiteDB;
import io.litedb.liteql.LTPredicate;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.liteql.statements.results.UpdateTableResult;
import io.litedb.planning.LTDBPlan;
import io.litedb.planning.LTFullTablePlan;
import io.litedb.scanning.LTWritableScan;
import io.litedb.tuples.LTSchema;
import io.litedb.tuples.data.LTBooleanData;
import io.litedb.tuples.data.LTIntegerData;
import io.litedb.tuples.data.LTTupleData;
import io.litedb.tuples.data.LTVarcharData;
import io.litedb.tuples.data.info.LTBooleanInfo;
import io.litedb.tuples.data.info.LTIntegerInfo;
import io.litedb.tuples.data.info.LTTupleDatumInfo;
import io.litedb.tuples.data.info.LTVarcharInfo;
import lombok.Getter;

public class LTUpdateTableStatement implements LTStatement {
    private @Getter String tableName;
    private @Getter Map<String, String> updateData;
    private @Getter List<LTPredicate> predicates;

    private @Getter LiteQLResult result;

    public LTUpdateTableStatement(String tableName, Map<String, String> updateData) {
        this.tableName = tableName;
        this.updateData = updateData;
    }

    public LTUpdateTableStatement(String tableName, Map<String, String> updateData, List<LTPredicate> predicates) {
        this.tableName = tableName;
        this.updateData = updateData;
        this.predicates = predicates;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            LTSchema schema = db.getOverseer().getTableSchema(tableName);
            Map<String, LTTupleData<?>> data = new HashMap<>();
            LTTupleData<?> value;
            for (String field: updateData.keySet()) {
                LTTupleDatumInfo info = schema.getFieldInfo(field);
                String rawValue = updateData.get(field);
                if (info instanceof LTIntegerInfo) {
                    value = new LTIntegerData(Integer.parseInt(rawValue));
                } else if (info instanceof LTBooleanInfo) {
                    value = new LTBooleanData(Boolean.parseBoolean(rawValue));
                } else if (info instanceof LTVarcharInfo){
                    LTVarcharInfo varcharInfo = (LTVarcharInfo) info;
                    String finalVal = rawValue.toString();
                    if (finalVal.startsWith("\'") && finalVal.endsWith("\'")) {
                        finalVal = finalVal.substring(1, finalVal.length() - 1);
                    }
                    value = new LTVarcharData(finalVal, varcharInfo.getMaxSize());
                } else {
                    throw new RuntimeException("Invalid data type");
                }

                data.put(field, value);
            }

            LTDBPlan plan = new LTFullTablePlan(
                tableName, 
                db.getStorageEngine(), 
                db.getOverseer(),
                db.getBufferManager(),
                true
            );
            LTWritableScan scan = (LTWritableScan) plan.start();
            
            int rowsUpdated = 0;

            if (predicates != null) {
                rowsUpdated = scan.update(data, predicates);
            } else {
                rowsUpdated = scan.update(data);
            }  
            
            this.result = new UpdateTableResult(tableName, rowsUpdated);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
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
            LTTupleData<?> value;
            for (String field: updateData.keySet()) {
                LTTupleDatumInfo info = schema.getFieldInfo(field);
                String rawValue = updateData.get(field);
                if (info instanceof LTIntegerInfo) {
                    value = new LTIntegerData(Integer.parseInt(rawValue));
                } else if (info instanceof LTBooleanInfo) {
                    value = new LTBooleanData(Boolean.parseBoolean(rawValue));
                } else if (info instanceof LTVarcharInfo){
                    LTVarcharInfo varcharInfo = (LTVarcharInfo) info;
                    String finalVal = rawValue.toString();
                    if (finalVal.startsWith("\'") && finalVal.endsWith("\'")) {
                        finalVal = finalVal.substring(1, finalVal.length() - 1);
                    }
                    value = new LTVarcharData(finalVal, varcharInfo.getMaxSize());
                } else {
                    throw new RuntimeException("Invalid data type");
                }

                data.put(field, value);
            }

            LTWritableScan scan = (LTWritableScan) plan.start();
            
            int rowsUpdated = 0;

            if (predicates != null) {
                rowsUpdated = scan.update(data, predicates);
            } else {
                rowsUpdated = scan.update(data);
            }  
            
            this.result = new UpdateTableResult(tableName, rowsUpdated);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
