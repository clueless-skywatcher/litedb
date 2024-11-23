package io.litedb.buffer;

import java.io.IOException;

import io.litedb.buffer.replacement.IBufferReplacementStrategy;
import io.litedb.buffer.replacement.NaiveReplacementStrategy;
import io.litedb.filesystem.BlockIdentifier;
import io.litedb.filesystem.LiteStorageEngine;

import lombok.Getter;

public class LiteBufferManager {
    public static final int BUFFER_POOL_SIZE = 10;

    private @Getter LiteBuffer[] bufferPool;
    private LiteStorageEngine storageEngine;
    private @Getter int availableBuffers;
    
    public LiteBufferManager(LiteStorageEngine storageEngine) {
        this.bufferPool = new LiteBuffer[BUFFER_POOL_SIZE];
        this.storageEngine = storageEngine;
        this.availableBuffers = bufferPool.length;

        for (int i = 0; i < BUFFER_POOL_SIZE; i++) {
            this.bufferPool[i] = new LiteBuffer();
        }
    }

    public void releaseBuffer(LiteBuffer buffer) {
        buffer.release();
        if (buffer.isReplaceable()) {
            availableBuffers++;
        }
    }

    private LiteBuffer findBufferHoldingBlock(BlockIdentifier block) {
        for (LiteBuffer buffer: bufferPool) {
            if (buffer.getTag() == null) {
                continue;
            }
            if (buffer.getTag().getBlockNumber() == block.getBlockNumber() 
                && buffer.getTag().getFileName().equals(block.getFileName())) {
                return buffer;
            }
        }

        return null;
    } 

    public LiteBuffer pinBuffer(BlockIdentifier block) throws IOException {
        LiteBuffer buffer = findBufferHoldingBlock(block);
        IBufferReplacementStrategy strategy = new NaiveReplacementStrategy(this);
        if (buffer == null) {
            buffer = strategy.chooseBuffer();
            buffer.loadBlock(block, storageEngine);
        }
        if (buffer.isReplaceable()) {
            availableBuffers--;
        }   
        buffer.pin();
        return buffer;
    }

    public void flushAll() throws IOException {
        for (LiteBuffer buffer: bufferPool) {
            buffer.flush(storageEngine);
        }
    }
}
