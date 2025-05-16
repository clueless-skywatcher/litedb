package io.litedb.buffer.replacement;

import io.litedb.buffer.LTBuffer;
import io.litedb.buffer.LTBufferManager;

public class LTNaiveReplacementStrategy implements LTBufferReplacementStrategy {
    private LTBufferManager bufferManager;

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
