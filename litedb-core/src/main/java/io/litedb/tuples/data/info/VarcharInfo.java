package io.litedb.tuples.data.info;

import lombok.Getter;

public class VarcharInfo implements TupleDatumInfo {
    private @Getter int size;
    
    public VarcharInfo(int charLength) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < charLength; i++) {
            b.append(" ");
        }
        this.size = Integer.BYTES + b.toString().getBytes().length;
    }
}
