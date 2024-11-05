package io.litedb.tuples.data;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import lombok.Getter;

public class VarcharData implements TupleData<String> {
    public @Getter String value;

    public VarcharData(String value) {
        this.value = value;
    }

    public TupleDataEnum getType() {
        return TupleDataEnum.VARCHAR;
    }

    public int getSize() {
        return Integer.BYTES + value.getBytes().length;
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
