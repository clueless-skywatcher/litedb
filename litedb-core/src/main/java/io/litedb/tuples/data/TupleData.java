package io.litedb.tuples.data;

public interface TupleData<T> {
    public int getSize();
    public byte[] serialize();
    public T getValue();

    public TupleDataEnum getType();
}
