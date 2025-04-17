package io.litedb.tuples.data;

import java.nio.ByteBuffer;

import io.litedb.tuples.TupleDataEnum;
import lombok.Getter;

public class BooleanData implements TupleData<Boolean> {
    public @Getter Boolean value;

    public BooleanData(boolean value) {
        this.value = value;
    }

    public int getSize() {
        return 1;
    }

    public TupleDataEnum getType() {
        return TupleDataEnum.BOOLEAN;
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
