package io.litedb.metadata.tables;

import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class ColumnsMetaMetaTable implements AbstractMetaTable {

    @Override
    public TableSchema getSchema() {
        TableSchema schema = new TableSchema();
        schema.addField("column_name", new VarcharInfo(100));
        schema.addField("table_name", new VarcharInfo(100));
        schema.addField("column_size", new IntegerInfo());
        schema.addField("column_type", new VarcharInfo(50));

        return schema;
    }

    @Override
    public String tableName() {
        return "columns_meta";
    }
    
}
