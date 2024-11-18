package io.litedb.liteql;

import io.litedb.tuples.LiteRow;
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

public class QueryPredicate {
    private @Getter String fieldName;
    private @Getter Object value;

    public QueryPredicate(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    private TupleData<?> inferData(TableSchema schema) {
        TupleDatumInfo info = schema.getFieldInfo(fieldName);
        if (info instanceof IntegerInfo) {
            return new IntegerData((int) value);
        } else if (info instanceof BooleanInfo) {
            return new BooleanData((boolean) value);
        } else if (info instanceof VarcharInfo){
            VarcharInfo varcharInfo = (VarcharInfo) info;
            String finalVal = value.toString();
            if (finalVal.startsWith("\'") && finalVal.endsWith("\'")) {
                finalVal = finalVal.substring(1, finalVal.length() - 1);
            }
            return new VarcharData(finalVal, varcharInfo.getMaxSize());
        } else {
            throw new RuntimeException("Invalid data type");
        }
    }

    public boolean rowSatisfies(LiteRow row, TableSchema schema) {
        TupleData<?> rowData = row.getData(fieldName);
        return rowData.equals(inferData(schema));
    }

    public boolean equals(Object other) {
        if (other instanceof QueryPredicate) {
            QueryPredicate otherPredicate = (QueryPredicate) other;
            return this.fieldName.equals(otherPredicate.fieldName) && this.value.equals(otherPredicate.value);
        }

        return false;
    }

    public String toString() {
        return String.format("%s = %s", fieldName, value.toString());
    }
}
