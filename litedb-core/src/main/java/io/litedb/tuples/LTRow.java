package io.litedb.tuples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.litedb.tuples.data.LTTupleData;

public class LTRow {
    private Map<String, LTTupleData<?>> data;
    
    public LTRow() {
        this.data = new HashMap<>();
    }

    public LTRow(Map<String, LTTupleData<?>> data) {
        this.data = data;
    }

    public void setData(String fieldName, LTTupleData<?> data) {
        this.data.put(fieldName, data);
    }

    public LTTupleData<?> getData(String fieldName) {
        return this.data.get(fieldName);
    }

    public Collection<String> getFields() {
        return this.data.keySet();
    }

    public String toString() {
        return data.toString();
    }
}
