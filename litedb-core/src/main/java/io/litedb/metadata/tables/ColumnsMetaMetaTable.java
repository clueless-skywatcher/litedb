package io.litedb.metadata.tables;

import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class ColumnsMetaMetaTable {
    public static TableSchema SCHEMA = new TableSchema()
        .addField("column_name", new VarcharInfo(100))
        .addField("table_name", new VarcharInfo(100))
        .addField("column_size", new IntegerInfo())
        .addField("column_type", new VarcharInfo(50));
        
    public static final String NAME = "columns_meta";
    
}
