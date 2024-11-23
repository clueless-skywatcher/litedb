package io.litedb.filesystem;

import io.litedb.tuples.data.TupleData;
import io.litedb.tuples.data.info.TupleDatumInfo;

public interface ILiteDBPage {
    public byte[] getContents();

    public int getInteger(int offset);
    public void setInteger(int offset, int value);
    
    public String getString(int offset);
    public void setString(int offset, String value);
    
    public boolean getBoolean(int offset);
    public void setBoolean(int offset, boolean value);
    
    public byte getByte(int offset);
    public void setByte(int offset, byte value);

    public TupleData<?> getData(int offset, TupleDatumInfo info);
    public void setData(int offset, TupleData<?> data);
}
