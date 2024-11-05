package io.litedb.filesystem;

import java.nio.ByteBuffer;

import io.litedb.tuples.data.BooleanData;
import io.litedb.tuples.data.IntegerData;
import io.litedb.tuples.data.TupleData;
import io.litedb.tuples.data.TupleDataEnum;
import io.litedb.tuples.data.VarcharData;
import io.litedb.tuples.data.info.BooleanInfo;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;

/**
 * Denotes a page in memory. This page is used to set and get data
 */
public class LitePage {
    private ByteBuffer contents;

    public LitePage(ByteBuffer contents) {
        this.contents = contents;
    }

    public LitePage(byte[] contents) {
        this.contents = ByteBuffer.wrap(contents);
    }

    public byte[] getContents() {
        return this.contents.array();
    }

    // Data setters and getters

    public int getInteger(int offset) {
        return contents.getInt(offset);
    }

    public void setInteger(int offset, int value) {
        contents.putInt(offset, value);
    }

    public String getString(int offset) {
        contents.position(offset);
        int length = contents.getInt();
        byte[] strBytes = new byte[length];
        contents.get(strBytes);
        return new String(strBytes);
    }

    public void setString(int offset, String value) {
        int length = value.length();
        contents.position(offset);
        contents.putInt(length);
        contents.put(value.getBytes());
        contents.position(0);
    }

    public boolean getBoolean(int offset) {
        int bool = contents.get(offset);
        return bool > 0 ? true : false;
    }

    public void setBoolean(int offset, boolean value) {
        contents.position(offset);
        contents.put((byte)(value ? 1 : 0));
        contents.position(0);
    }

    public TupleData<?> getData(int offset, TupleDatumInfo info) {
        if (info instanceof IntegerInfo) {
            return new IntegerData(getInteger(offset));
        } else if (info instanceof VarcharInfo) {
            return new VarcharData(getString(offset), ((VarcharInfo) info).getMaxSize());
        } else if (info instanceof BooleanInfo) {
            return new BooleanData(getBoolean(offset));
        } else {
            throw new RuntimeException("Not a valid datatype");
        }
    }

    public void setData(int offset, TupleData<?> data) {
        contents.put(offset, data.serialize());
    }
}
