package io.litedb.buffer.replacement;

import io.litedb.buffer.LTBuffer;

public interface LTBufferReplacementStrategy {
    public LTBuffer chooseBuffer();
}
