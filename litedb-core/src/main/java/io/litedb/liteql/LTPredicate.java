package io.litedb.liteql;

import io.litedb.tuples.LTRow;
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

public class LTPredicate {
    private @Getter String fieldName;
    private @Getter Object value;

    public LTPredicate(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    private LTTupleData<?> inferData(LTSchema schema) {
        LTTupleDatumInfo info = schema.getFieldInfo(fieldName);
        if (info instanceof LTIntegerInfo) {
            return new LTIntegerData((int) value);
        } else if (info instanceof LTBooleanInfo) {
            return new LTBooleanData((boolean) value);
        } else if (info instanceof LTVarcharInfo){
            LTVarcharInfo varcharInfo = (LTVarcharInfo) info;
            String finalVal = value.toString();
            if (finalVal.startsWith("\'") && finalVal.endsWith("\'")) {
                finalVal = finalVal.substring(1, finalVal.length() - 1);
            }
            return new LTVarcharData(finalVal, varcharInfo.getMaxSize());
        } else {
            throw new RuntimeException("Invalid data type");
        }
    }

    public boolean rowSatisfies(LTRow row, LTSchema schema) {
        LTTupleData<?> rowData = row.getData(fieldName);
        return rowData.equals(inferData(schema));
    }

    public boolean equals(Object other) {
        if (other instanceof LTPredicate) {
            LTPredicate otherPredicate = (LTPredicate) other;
            return this.fieldName.equals(otherPredicate.fieldName) && this.value.equals(otherPredicate.value);
        }

        return false;
    }

    public String toString() {
        return String.format("%s = %s", fieldName, value.toString());
    }
}
