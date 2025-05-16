package io.litedb.tuples.data.info;

import lombok.Getter;

public class LTBooleanInfo implements LTTupleDatumInfo {
    private @Getter int size = 1;

    @Override
    public String getTypeString() {
        return "boolean";
    }

    public boolean equals(Object other) {
        return other instanceof LTBooleanInfo;
    }
}
