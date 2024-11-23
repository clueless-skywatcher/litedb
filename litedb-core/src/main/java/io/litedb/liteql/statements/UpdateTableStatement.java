package io.litedb.liteql.statements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.litedb.LiteDB;
import io.litedb.liteql.QueryPredicate;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.liteql.statements.results.UpdateTableResult;
import io.litedb.planning.DBPlan;
import io.litedb.planning.FullTablePlan;
import io.litedb.scanning.WritableScan;
import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.BooleanData;
import io.litedb.tuples.data.IntegerData;
import io.litedb.tuples.data.TupleData;
import io.litedb.tuples.data.VarcharData;
import io.litedb.tuples.data.info.BooleanInfo;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;
import lombok.Getter;

public class UpdateTableStatement implements LiteQLStatement {
    private String tableName;
    private Map<String, String> updateData;
    private List<QueryPredicate> predicates;

    private @Getter LiteQLResult result;

    public UpdateTableStatement(String tableName, Map<String, String> updateData) {
        this.tableName = tableName;
        this.updateData = updateData;
    }

    public UpdateTableStatement(String tableName, Map<String, String> updateData, List<QueryPredicate> predicates) {
        this.tableName = tableName;
        this.updateData = updateData;
        this.predicates = predicates;
    }

    @Override
    public void execute(LiteDB db) {
        try {
            TableSchema schema = db.getOverseer().getTableSchema(tableName);
            Map<String, TupleData<?>> data = new HashMap<>();
            TupleData<?> value;
            for (String field: updateData.keySet()) {
                TupleDatumInfo info = schema.getFieldInfo(field);
                String rawValue = updateData.get(field);
                if (info instanceof IntegerInfo) {
                    value = new IntegerData(Integer.parseInt(rawValue));
                } else if (info instanceof BooleanInfo) {
                    value = new BooleanData(Boolean.parseBoolean(rawValue));
                } else if (info instanceof VarcharInfo){
                    VarcharInfo varcharInfo = (VarcharInfo) info;
                    String finalVal = rawValue.toString();
                    if (finalVal.startsWith("\'") && finalVal.endsWith("\'")) {
                        finalVal = finalVal.substring(1, finalVal.length() - 1);
                    }
                    value = new VarcharData(finalVal, varcharInfo.getMaxSize());
                } else {
                    throw new RuntimeException("Invalid data type");
                }

                data.put(field, value);
            }

            DBPlan plan = new FullTablePlan(
                tableName, 
                db.getStorageEngine(), 
                db.getOverseer(),
                db.getBufferManager(),
                true
            );
            WritableScan scan = (WritableScan) plan.start();
            
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
