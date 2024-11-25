package io.litedb.tuples;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import io.litedb.tuples.data.TupleData;

public class LiteRow {
    private Map<String, TupleData<?>> data;
    
    public LiteRow() {
        this.data = new LinkedHashMap<>();
    }

    public LiteRow(Map<String, TupleData<?>> data) {
        this.data = data;
    }

    public void setData(String fieldName, TupleData<?> data) {
        this.data.put(fieldName, data);
    }

    public TupleData<?> getData(String fieldName) {
        return this.data.get(fieldName);
    }

    public Collection<String> getFields() {
        return this.data.keySet();
    }

    public String toString() {
        return data.toString();
    }
}
