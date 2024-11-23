package io.litedb.buffer.replacement;

import io.litedb.buffer.LiteBuffer;

public interface IBufferReplacementStrategy {
    public LiteBuffer chooseBuffer();
}
