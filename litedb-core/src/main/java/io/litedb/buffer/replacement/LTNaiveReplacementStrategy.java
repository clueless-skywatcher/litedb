package io.litedb.buffer.replacement;

import io.litedb.buffer.LTBuffer;
import io.litedb.buffer.LTBufferManager;

public class LTNaiveReplacementStrategy implements LTBufferReplacementStrategy {
    private LTBufferManager bufferManager;

    /**
     * The Naive strategy loops through each buffer in the buffer pool, 
     * finds the first buffer that has no pins on it and simply returns it
     * @param bufferManager
     */
    public LTNaiveReplacementStrategy(LTBufferManager bufferManager) {
        this.bufferManager = bufferManager;
    }

    @Override
    public LTBuffer chooseBuffer() {
        for (LTBuffer buffer: bufferManager.getBufferPool()) {
            if (buffer.isReplaceable()) {
                return buffer;
            }
        }

        return null;
    }
}
