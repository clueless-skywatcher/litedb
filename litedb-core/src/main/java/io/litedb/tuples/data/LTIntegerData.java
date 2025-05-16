package io.litedb.tuples.data;

import java.nio.ByteBuffer;

import lombok.Getter;

public class LTIntegerData implements LTTupleData<Integer> {
    public @Getter Integer value;

    public LTIntegerData(int value) {
        this.value = value;
    }

    public int getSize() {
        return Integer.BYTES;
    }

    public LTTupleDataEnum getType() {
        return LTTupleDataEnum.INTEGER;
    }

    public byte[] serialize() {
        byte[] bytes = ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
        return bytes;
    }

    public boolean equals(Object other) {
        if (other instanceof LTIntegerData) {
            return this.value.equals(((LTIntegerData) other).value);
        }
        return false;
    }

    public String toString() {
        return Integer.toString(value);
    }
}
