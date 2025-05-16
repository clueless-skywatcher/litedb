package io.litedb.tuples;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import io.litedb.tuples.data.info.TupleDatumInfo;

public class TableSchema {
    private Map<String, TupleDatumInfo> fields;
    private Map<String, Integer> offsets;

    public TableSchema(Map<String, TupleDatumInfo> fields) {
        this.fields = fields;
        updateOffsets();
    }

    public TableSchema() {
        this.fields = new HashMap<>();
        updateOffsets();
    }

    private void updateOffsets() {
        this.offsets = new HashMap<>();

        int currentOffset = 0;

        for (String field: fields.keySet()) {
            this.offsets.put(field, currentOffset);
            TupleDatumInfo info = fields.get(field);
            currentOffset += info.getSize();
        }
    }

    public TableSchema addField(String fieldName, TupleDatumInfo info) {
        this.fields.put(fieldName, info);
        updateOffsets();
        return this;
    }

    public TableSchema addFields(Map<String, TupleDatumInfo> fields) {
        for (Map.Entry<String, TupleDatumInfo> entry: fields.entrySet()) {
            this.fields.put(entry.getKey(), entry.getValue());
        }
        updateOffsets();
        return this;
    }

    public TupleDatumInfo getFieldInfo(String fieldName) {
        return fields.get(fieldName);
    }

    public int getOffset(String fieldName) {
        return offsets.get(fieldName);
    }

    public int getSize() {
        int size = 0;
        for (TupleDatumInfo info: fields.values()) {
            size += info.getSize();
        }
        // 1 extra byte will be required to store the dirty byte
        return size + 1;
    }

    public Collection<String> getFields() {
        return fields.keySet();
    }

    public boolean hasField(String string) {
        return this.fields.containsKey(string);
    }

    public TableSchema appendSchema(TableSchema other) {
        TableSchema newSchema = new TableSchema(fields);
        newSchema.addFields(other.fields);
        return newSchema;
    }
}
