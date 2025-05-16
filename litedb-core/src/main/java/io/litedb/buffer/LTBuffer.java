package io.litedb.buffer;

import java.io.IOException;

import io.litedb.filesystem.LTBlockIdentifier;
import io.litedb.filesystem.LTDBFile;
import io.litedb.filesystem.LTDBPage;
import io.litedb.filesystem.LTFile;
import io.litedb.filesystem.LTPage;
import io.litedb.filesystem.LTStorageEngine;
import lombok.Getter;

public class LTBuffer {
    private @Getter LTDBPage page;
    private @Getter LTBufferTag tag;
    private int pins;

    /** 
     * A Buffer is a cache that stores temporary bytes
     * to enhance reuse of data without loading it from disk
     */
    public LTBuffer() {
        this.page = new LTPage(LTFile.BLOCK_SIZE);
        this.pins = 0;
    }

    /**
     * Checks if the current buffer has no pins by any transaction
     * @return
     */
    public boolean isReplaceable() {
        return pins == 0;
    }

    /**
     * Pin the buffer
     */
    public void pin() {
        pins++;
    }

    /**
     * Release a pin of the buffer (if there is one)
     */
    public void release() {
        if (pins > 0) {
            pins--;
        }
    }

    /**
     * Load a block of data from the disk into the buffer
     * for caching
     * @param block
     * @param engine
     * @throws IOException
     */
    public void loadBlock(LTBlockIdentifier block, LTStorageEngine engine) throws IOException {
        flush(engine);
        tag = new LTBufferTag(block.getFileName(), block.getBlockNumber());
        LTDBFile file = engine.getFile(tag.getFileName());
        page = file.readBlock(block);
    }

    /**
     * Flush the data held in the buffer to the disk
     * @param engine
     * @throws IOException
     */
    public void flush(LTStorageEngine engine) throws IOException {
        if (tag != null) {
            LTDBFile file = engine.getFile(tag.getFileName());
            file.writeBlock(tag.getBlockNumber(), page);
        }
    }
}
