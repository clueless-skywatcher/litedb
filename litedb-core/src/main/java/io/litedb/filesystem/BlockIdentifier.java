package io.litedb.filesystem;

import java.io.IOException;

import lombok.Getter;

public class BlockIdentifier {
    private @Getter int blockNumber;
    private @Getter int blockSize;
    private @Getter String fileName;
    
    public BlockIdentifier(String fileName, int blockNumber, int blockSize) throws IOException {
        this.fileName = fileName;
        this.blockNumber = blockNumber;
        this.blockSize = blockSize;
    }

    public boolean equals(Object other) {
        if (other instanceof BlockIdentifier) {
            BlockIdentifier otherBlock = (BlockIdentifier) other;
            return this.blockNumber == otherBlock.blockNumber 
                && this.blockSize == otherBlock.blockSize
                && this.fileName == otherBlock.fileName;
        }

        return false;
    }
}
