package io.litedb.filesystem;

import java.io.IOException;

public interface ILiteDBFile {
    public void appendNewBlock(int blockNumber) throws IOException;
    public ILiteDBPage readBlock(BlockIdentifier id);
    public ILiteDBPage readBlock(int id) throws IOException;
    public void writeBlock(BlockIdentifier id, ILiteDBPage page);
    public void writeBlock(int id, ILiteDBPage page) throws IOException;
    public void close();
    public long getBlockCount();
    public void validateBlock(BlockIdentifier id);
}
