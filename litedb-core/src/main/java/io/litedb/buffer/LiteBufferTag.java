package io.litedb.buffer;

import lombok.Getter;

public class LiteBufferTag {
    private @Getter String fileName;
    private @Getter int blockNumber;
    
    public LiteBufferTag(String fileName, int blockNumber) {
        this.fileName = fileName;
        this.blockNumber = blockNumber;
    }

    public boolean equals(Object other) {
        if (other instanceof LiteBufferTag) {
            LiteBufferTag otherTag = (LiteBufferTag) other;
            return fileName.equals(otherTag.fileName) && blockNumber == otherTag.blockNumber;
        }

        return false;
    }

    public int hashCode() {
        return String.format("%s_%d", this.fileName, this.blockNumber).hashCode();
    }
}
