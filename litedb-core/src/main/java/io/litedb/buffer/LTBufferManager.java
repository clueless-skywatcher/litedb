package io.litedb.buffer;

import java.io.IOException;

import io.litedb.buffer.replacement.LTBufferReplacementStrategy;
import io.litedb.buffer.replacement.LTNaiveReplacementStrategy;
import io.litedb.filesystem.LTBlockIdentifier;
import io.litedb.filesystem.LTStorageEngine;

import lombok.Getter;

public class LTBufferManager {
    public static final int BUFFER_POOL_SIZE = 10;

    private @Getter LTBuffer[] bufferPool;
    private LTStorageEngine storageEngine;
    private @Getter int availableBuffers;
    
    /**
     * Buffer Pool manager class.
     * 
     * Calling this initialises an array of empty buffer objects (LiteBuffer)
     * each of which can hold a page
     * @param storageEngine
     */
    public LTBufferManager(LTStorageEngine storageEngine) {
        this.bufferPool = new LTBuffer[BUFFER_POOL_SIZE];
        this.storageEngine = storageEngine;
        this.availableBuffers = bufferPool.length;

        for (int i = 0; i < BUFFER_POOL_SIZE; i++) {
            this.bufferPool[i] = new LTBuffer();
        }
    }

    /**
     * Release a buffer from use, reducing the number of pins
     * on it. If there are no pins on the buffer, make it available
     * for replacement.
     * @param buffer
     */
    public void releaseBuffer(LTBuffer buffer) {
        buffer.release();
        if (buffer.isReplaceable()) {
            availableBuffers++;
        }
    }

    /**
     * Go through the buffer pool and find out which buffer holds
     * a particular block, denoted by the buffer's tag
     * @param block
     * @return
     */
    private LTBuffer findBufferHoldingBlock(LTBlockIdentifier block) {
        for (LTBuffer buffer: bufferPool) {
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

    /**
     * Pin a buffer holding a page/block, i.e. mark it being used by a transaction, 
     * then return it. If no buffers hold the page/block, a buffer is chosen to be replaced
     * as per the replacement policy, and the block is loaded into it
     * @param block
     * @return
     * @throws IOException
     */
    public LTBuffer pinBuffer(LTBlockIdentifier block) throws IOException {
        LTBuffer buffer = findBufferHoldingBlock(block);
        LTBufferReplacementStrategy strategy = new LTNaiveReplacementStrategy(this);
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

    /**
     * Flush all the stored buffers to disk
     * @throws IOException
     */
    public void flushAll() throws IOException {
        for (LTBuffer buffer: bufferPool) {
            buffer.flush(storageEngine);
        }
    }
}
