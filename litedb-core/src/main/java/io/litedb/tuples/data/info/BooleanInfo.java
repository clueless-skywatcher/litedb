package io.litedb.tuples.data.info;

import lombok.Getter;

public class BooleanInfo implements TupleDatumInfo {
    private @Getter int size = 1;

    @Override
    public String getTypeString() {
        return "boolean";
    }
}
