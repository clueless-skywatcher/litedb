package io.litedb.tuples.data.info;

import lombok.Getter;

public class IntegerInfo implements TupleDatumInfo {
    private @Getter int size = Integer.BYTES;
}
