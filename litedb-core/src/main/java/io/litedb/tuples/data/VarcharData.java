package io.litedb.tuples.data;

import java.nio.ByteBuffer;

import lombok.Getter;

public class VarcharData implements TupleData<String> {
    private @Getter String value;

    public VarcharData(String value, int maxCharLength) {
        this.value = value.substring(0, maxCharLength);
    }

    public TupleDataEnum getType() {
        return TupleDataEnum.VARCHAR;
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
        if (other instanceof VarcharData) {
            return this.value.equals(((VarcharData) other).value);
        }
        return false;
    }
}
