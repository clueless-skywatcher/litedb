package io.litedb.tuples.data;

public interface LTTupleData<T> {
    public int getSize();
    public byte[] serialize();
    public T getValue();

    public LTTupleDataEnum getType();
}
