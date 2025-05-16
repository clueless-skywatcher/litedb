package io.litedb.tuples.data;

import java.nio.ByteBuffer;

import lombok.Getter;

public class LTVarcharData implements LTTupleData<String> {
    private @Getter String value;

    public LTVarcharData(String value, int maxCharLength) {
        this.value = value.substring(0, Math.min(maxCharLength, value.length()));
    }

    public LTTupleDataEnum getType() {
        return LTTupleDataEnum.VARCHAR;
    }

    public int getSize() {
        return Integer.BYTES + value.length();
    }

    public byte[] serialize() {
        ByteBuffer buf = ByteBuffer.allocate(getSize());
        buf.putInt(value.length());
        buf.put(value.getBytes());
        return buf.array();
    }

    public boolean equals(Object other) {
        if (other instanceof LTVarcharData) {
            return this.value.equals(((LTVarcharData) other).value);
        }
        return false;
    }

    public String toString() {
        return value;
    }
}
