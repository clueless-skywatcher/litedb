package io.litedb.tuples.data;

import java.nio.ByteBuffer;

import io.litedb.tuples.TupleDataEnum;
import lombok.Getter;

public class IntegerData implements TupleData<Integer> {
    public @Getter Integer value;

    public IntegerData(int value) {
        this.value = value;
    }

    public int getSize() {
        return Integer.BYTES;
    }

    public TupleDataEnum getType() {
        return TupleDataEnum.INTEGER;
    }

    public byte[] serialize() {
        byte[] bytes = ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
        return bytes;
    }

    public boolean equals(Object other) {
        if (other instanceof IntegerData) {
            return this.value.equals(((IntegerData) other).value);
        }
        return false;
    }

    public String toString() {
        return Integer.toString(value);
    }
}
