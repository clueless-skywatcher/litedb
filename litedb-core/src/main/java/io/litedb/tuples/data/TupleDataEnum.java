package io.litedb.tuples.data;

import lombok.Getter;

public enum TupleDataEnum {
    INTEGER(0),
    BOOLEAN(1),
    VARCHAR(2);

    private @Getter int type;

    private TupleDataEnum(int type) {
        this.type = type;
    }
}
