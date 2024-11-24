package io.litedb.buffer.replacement;

import io.litedb.buffer.LiteBuffer;
import io.litedb.buffer.LiteBufferManager;

public class NaiveReplacementStrategy implements IBufferReplacementStrategy {
    private LiteBufferManager bufferManager;

    public NaiveReplacementStrategy(LiteBufferManager bufferManager) {
        this.bufferManager = bufferManager;
    }

    @Override
    public LiteBuffer chooseBuffer() {
        for (LiteBuffer buffer: bufferManager.getBufferPool()) {
            if (buffer.isReplaceable()) {
                return buffer;
            }
        }

        return null;
    }
}
