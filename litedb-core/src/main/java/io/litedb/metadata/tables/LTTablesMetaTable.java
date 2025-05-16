package io.litedb.metadata.tables;

import io.litedb.tuples.LTSchema;
import io.litedb.tuples.data.info.LTIntegerInfo;
import io.litedb.tuples.data.info.LTVarcharInfo;

public class LTTablesMetaTable {
    public static LTSchema SCHEMA = new LTSchema()
        .addField("table_name", new LTVarcharInfo(100))
        .addField("row_size", new LTIntegerInfo());
        
    public static final String NAME = "tables_meta";
}
