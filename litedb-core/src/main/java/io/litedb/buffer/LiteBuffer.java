package io.litedb.buffer;

import java.io.IOException;

import io.litedb.filesystem.BlockIdentifier;
import io.litedb.filesystem.ILiteDBFile;
import io.litedb.filesystem.ILiteDBPage;
import io.litedb.filesystem.LiteFile;
import io.litedb.filesystem.LitePage;
import io.litedb.filesystem.LiteStorageEngine;
import lombok.Getter;

public class LiteBuffer {
    private @Getter ILiteDBPage page;
    private @Getter LiteBufferTag tag;
    private int pins;

    public LiteBuffer(LiteBufferTag tag) {
        this.page = new LitePage(LiteFile.BLOCK_SIZE);
        this.tag = tag;
        this.pins = 0;
    }

    public LiteBuffer() {
        this.page = new LitePage(LiteFile.BLOCK_SIZE);
        this.pins = 0;
    }

    public boolean isReplaceable() {
        return pins == 0;
    }

    public void pin() {
        pins++;
    }

    public void release() {
        if (pins > 0) {
            pins--;
        }
    }

    public void loadBlock(BlockIdentifier block, LiteStorageEngine engine) throws IOException {
        flush(engine);
        tag = new LiteBufferTag(block.getFileName(), block.getBlockNumber());
        ILiteDBFile file = engine.getFile(tag.getFileName());
        page = file.readBlock(block);
    }

    public void flush(LiteStorageEngine engine) throws IOException {
        if (tag != null) {
            ILiteDBFile file = engine.getFile(tag.getFileName());
            file.writeBlock(tag.getBlockNumber(), page);
        }
    }
}
