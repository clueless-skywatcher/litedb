package io.litedb.filesystem;

import java.io.IOException;

public interface LTDBFile {
    public void appendNewBlock(int blockNumber) throws IOException;
    public LTDBPage readBlock(LTBlockIdentifier id);
    public LTDBPage readBlock(int id) throws IOException;
    public void writeBlock(LTBlockIdentifier id, LTDBPage page);
    public void writeBlock(int id, LTDBPage page) throws IOException;
    public void close();
    public long getBlockCount();
    public void validateBlock(LTBlockIdentifier id);
}
