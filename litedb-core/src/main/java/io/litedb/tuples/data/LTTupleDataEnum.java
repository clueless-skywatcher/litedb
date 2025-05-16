package io.litedb.tuples.data;

import lombok.Getter;

public enum LTTupleDataEnum {
    INTEGER(0),
    BOOLEAN(1),
    VARCHAR(2);

    private @Getter int type;

    private LTTupleDataEnum(int type) {
        this.type = type;
    }
}
