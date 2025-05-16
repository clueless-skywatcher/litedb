package io.litedb.tuples;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import io.litedb.tuples.data.info.LTTupleDatumInfo;

public class LTSchema {
    private Map<String, LTTupleDatumInfo> fields;
    private Map<String, Integer> offsets;

    public LTSchema(Map<String, LTTupleDatumInfo> fields) {
        this.fields = fields;
        updateOffsets();
    }

    public LTSchema() {
        this.fields = new HashMap<>();
        updateOffsets();
    }

    private void updateOffsets() {
        this.offsets = new HashMap<>();

        int currentOffset = 0;

        for (String field: fields.keySet()) {
            this.offsets.put(field, currentOffset);
            LTTupleDatumInfo info = fields.get(field);
            currentOffset += info.getSize();
        }
    }

    public LTSchema addField(String fieldName, LTTupleDatumInfo info) {
        this.fields.put(fieldName, info);
        updateOffsets();
        return this;
    }

    public LTSchema addFields(Map<String, LTTupleDatumInfo> fields) {
        for (Map.Entry<String, LTTupleDatumInfo> entry: fields.entrySet()) {
            this.fields.put(entry.getKey(), entry.getValue());
        }
        updateOffsets();
        return this;
    }

    public LTTupleDatumInfo getFieldInfo(String fieldName) {
        return fields.get(fieldName);
    }

    public int getOffset(String fieldName) {
        return offsets.get(fieldName);
    }

    public int getSize() {
        int size = 0;
        for (LTTupleDatumInfo info: fields.values()) {
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

    public LTSchema appendSchema(LTSchema other) {
        LTSchema newSchema = new LTSchema(fields);
        newSchema.addFields(other.fields);
        return newSchema;
    }
}
