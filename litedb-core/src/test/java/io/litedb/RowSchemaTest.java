package io.litedb;

import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.litedb.tuples.RowSchema;
import io.litedb.tuples.data.info.BooleanInfo;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class RowSchemaTest {
    @Test
    public void testFields() {
        Map<String, TupleDatumInfo> fields = new TreeMap<>();
        fields.put("field1", new IntegerInfo());
        fields.put("field2", new VarcharInfo(85));
        fields.put("field3", new BooleanInfo());

        RowSchema schema = new RowSchema(fields);

        Assertions.assertEquals(schema.getSize(), 94);

        Assertions.assertEquals(schema.getOffset("field1"), 0);
        Assertions.assertEquals(schema.getOffset("field2"), 4);
        Assertions.assertEquals(schema.getOffset("field3"), 93);

        Assertions.assertEquals(schema.getFields(), Set.of("field1", "field2", "field3"));

        Map<String, TupleDatumInfo> newFields = new TreeMap<>();
        newFields.put("field4", new VarcharInfo(25));
        newFields.put("field5", new IntegerInfo());

        schema.addFields(newFields);

        Assertions.assertEquals(schema.getSize(), 127);

        Assertions.assertEquals(schema.getFields(), Set.of("field1", "field2", "field3", "field4", "field5"));
    }
}
