package io.litedb.tuples.data.info;

import lombok.Getter;

public class VarcharInfo implements TupleDatumInfo {
    private @Getter int size;
    private @Getter int maxSize;
    
    public VarcharInfo(int charLength) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < charLength; i++) {
            b.append(" ");
        }
        this.maxSize = b.toString().getBytes().length;
        this.size = Integer.BYTES + maxSize;
    }
}