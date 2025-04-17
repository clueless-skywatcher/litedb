package io.litedb.tuples.data;

import io.litedb.tuples.TupleDataEnum;

public interface TupleData<T> {
    public int getSize();
    public byte[] serialize();
    public T getValue();

    public TupleDataEnum getType();
}
