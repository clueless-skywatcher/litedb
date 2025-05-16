package io.litedb.metadata.tables;

import io.litedb.tuples.LTSchema;
import io.litedb.tuples.data.info.LTIntegerInfo;
import io.litedb.tuples.data.info.LTVarcharInfo;

public class LTColumnsMetaTable {
    public static LTSchema SCHEMA = new LTSchema()
        .addField("column_name", new LTVarcharInfo(100))
        .addField("table_name", new LTVarcharInfo(100))
        .addField("column_size", new LTIntegerInfo())
        .addField("column_type", new LTVarcharInfo(50));
        
    public static final String NAME = "columns_meta";
    
}
