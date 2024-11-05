package io.litedb.filesystem;

import java.io.RandomAccessFile;

import lombok.Getter;

public class BlockIdentifier {
    private @Getter int blockNumber;
    private @Getter int blockSize;
    private @Getter RandomAccessFile raf;

    private @Getter byte[] contents;

    public BlockIdentifier(RandomAccessFile raf, int blockNumber, int blockSize) {
        this.raf = raf;
        this.blockNumber = blockNumber;
        this.blockSize = blockSize;
        read();
    }

    private void read() {
        raf.readFully(contents, blockNumber * blockSize, blockSize);
    }

    public void write() {
        raf.write(contents, blockNumber * blockSize, blockSize);
    }
}
