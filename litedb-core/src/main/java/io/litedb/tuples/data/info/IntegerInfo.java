package io.litedb.tuples.data.info;

import lombok.Getter;

public class IntegerInfo implements TupleDatumInfo {
    private @Getter int size = Integer.BYTES;

    @Override
    public String getTypeString() {
        return "int";
    }

    public boolean equals(Object other) {
        return other instanceof IntegerInfo;
    }
}
