package io.litedb.filesystem;

import java.io.IOException;

import lombok.Getter;

public class LTBlockIdentifier {
    private @Getter int blockNumber;
    private @Getter int blockSize;
    private @Getter String fileName;
    
    public LTBlockIdentifier(String fileName, int blockNumber, int blockSize) throws IOException {
        this.fileName = fileName;
        this.blockNumber = blockNumber;
        this.blockSize = blockSize;
    }

    public boolean equals(Object other) {
        if (other instanceof LTBlockIdentifier) {
            LTBlockIdentifier otherBlock = (LTBlockIdentifier) other;
            return this.blockNumber == otherBlock.blockNumber 
                && this.blockSize == otherBlock.blockSize
                && this.fileName == otherBlock.fileName;
        }

        return false;
    }
}
