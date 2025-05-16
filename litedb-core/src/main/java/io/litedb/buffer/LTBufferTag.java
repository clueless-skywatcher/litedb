package io.litedb.buffer;

import lombok.Getter;

public class LTBufferTag {
    private @Getter String fileName;
    private @Getter int blockNumber;
    
    public LTBufferTag(String fileName, int blockNumber) {
        this.fileName = fileName;
        this.blockNumber = blockNumber;
    }

    public boolean equals(Object other) {
        if (other instanceof LTBufferTag) {
            LTBufferTag otherTag = (LTBufferTag) other;
            return fileName.equals(otherTag.fileName) && blockNumber == otherTag.blockNumber;
        }

        return false;
    }

    public int hashCode() {
        return String.format("%s_%d", this.fileName, this.blockNumber).hashCode();
    }
}
