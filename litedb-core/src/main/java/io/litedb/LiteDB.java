package io.litedb;

import java.util.Map;
import java.util.TreeMap;

import io.litedb.tuples.RowSchema;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class LiteDB 
{
    public static void main( String[] args )
    {
        Map<String, TupleDatumInfo> fields = new TreeMap<>();
        fields.put("roll_number", new IntegerInfo());
        fields.put("name", new VarcharInfo(200));

        RowSchema schema = new RowSchema(fields);
    }
}
