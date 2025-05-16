package io.litedb.tuples.data;

import java.nio.ByteBuffer;

import lombok.Getter;

public class LTBooleanData implements LTTupleData<Boolean> {
    public @Getter Boolean value;

    public LTBooleanData(boolean value) {
        this.value = value;
    }

    public int getSize() {
        return 1;
    }

    public LTTupleDataEnum getType() {
        return LTTupleDataEnum.BOOLEAN;
    }

    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        buffer.put((byte)(value ? 1 : 0));
        return buffer.array();
    }

    public String toString() {
        return Boolean.toString(value);
    }
}
