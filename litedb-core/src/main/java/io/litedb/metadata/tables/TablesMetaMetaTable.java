package io.litedb.metadata.tables;

import io.litedb.tuples.TableSchema;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class TablesMetaMetaTable implements AbstractMetaTable {
    @Override
    public TableSchema getSchema() {
        TableSchema schema = new TableSchema();
        schema.addField("table_name", new VarcharInfo(100));
        schema.addField("row_size", new IntegerInfo());

        return schema;
    }

    @Override
    public String tableName() {
        return "tables_meta";
    }
}
