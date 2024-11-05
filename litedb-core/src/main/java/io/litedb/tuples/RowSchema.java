package io.litedb.tuples;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.litedb.tuples.data.info.TupleDatumInfo;

public class RowSchema {
    private Map<String, TupleDatumInfo> fields;
    private Map<String, Integer> offsets;

    public RowSchema(Map<String, TupleDatumInfo> fields) {
        this.fields = fields;
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

    public void addField(String fieldName, TupleDatumInfo info) {
        this.fields.put(fieldName, info);
        updateOffsets();
    }

    public void addFields(Map<String, TupleDatumInfo> fields) {
        for (Map.Entry<String, TupleDatumInfo> entry: fields.entrySet()) {
            this.fields.put(entry.getKey(), entry.getValue());
        }
        updateOffsets();
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
        // 1 extra byte will be required to retrieve the dirty byte
        return size + 1;
    }

    public Collection<String> getFields() {
        return fields.keySet();
    }
}
