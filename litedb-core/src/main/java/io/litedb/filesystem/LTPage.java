package io.litedb.filesystem;

import java.nio.ByteBuffer;

import io.litedb.tuples.data.LTBooleanData;
import io.litedb.tuples.data.LTIntegerData;
import io.litedb.tuples.data.LTTupleData;
import io.litedb.tuples.data.LTVarcharData;
import io.litedb.tuples.data.info.LTBooleanInfo;
import io.litedb.tuples.data.info.LTIntegerInfo;
import io.litedb.tuples.data.info.LTTupleDatumInfo;
import io.litedb.tuples.data.info.LTVarcharInfo;

/**
 * Denotes a page in memory. This page is used to set and get data
 */
public class LTPage implements LTDBPage {
    private ByteBuffer contents;

    public LTPage(ByteBuffer contents) {
        this.contents = contents;
    }

    public LTPage(byte[] contents) {
        this.contents = ByteBuffer.wrap(contents);
    }

    public LTPage(int blockSize) {
        this.contents = ByteBuffer.allocate(blockSize);
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

    public byte getByte(int offset) {
        return contents.get(offset);
    }

    public void setByte(int offset, byte value) {
        contents.position(offset);
        contents.put(value);
        contents.position(0);
    }

    public LTTupleData<?> getData(int offset, LTTupleDatumInfo info) {
        if (info instanceof LTIntegerInfo) {
            return new LTIntegerData(getInteger(offset));
        } else if (info instanceof LTVarcharInfo) {
            return new LTVarcharData(getString(offset), ((LTVarcharInfo) info).getMaxSize());
        } else if (info instanceof LTBooleanInfo) {
            return new LTBooleanData(getBoolean(offset));
        } else {
            throw new RuntimeException("Not a valid datatype");
        }
    }

    public void setData(int offset, LTTupleData<?> data) {
        contents.put(offset, data.serialize());
    }
}
