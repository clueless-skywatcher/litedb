package io.litedb.metadata.tables;

import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class TablesMetaMetaTable {
    public static TableSchema SCHEMA = new TableSchema()
        .addField("table_name", new VarcharInfo(100))
        .addField("row_size", new IntegerInfo());
        
    public static final String NAME = "tables_meta";
}
