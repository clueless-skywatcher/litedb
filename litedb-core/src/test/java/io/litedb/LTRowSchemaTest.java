package io.litedb;

import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.litedb.tuples.LTSchema;
import io.litedb.tuples.data.info.LTBooleanInfo;
import io.litedb.tuples.data.info.LTIntegerInfo;
import io.litedb.tuples.data.info.LTTupleDatumInfo;
import io.litedb.tuples.data.info.LTVarcharInfo;

public class LTRowSchemaTest {
    @Test
    public void testFields() {
        Map<String, LTTupleDatumInfo> fields = new TreeMap<>();
        fields.put("field1", new LTIntegerInfo());
        fields.put("field2", new LTVarcharInfo(85));
        fields.put("field3", new LTBooleanInfo());

        LTSchema schema = new LTSchema(fields);

        Assertions.assertEquals(schema.getSize(), 95);

        Assertions.assertEquals(schema.getOffset("field1"), 0);
        Assertions.assertEquals(schema.getOffset("field2"), 4);
        Assertions.assertEquals(schema.getOffset("field3"), 93);

        Assertions.assertEquals(schema.getFields(), Set.of("field1", "field2", "field3"));

        Map<String, LTTupleDatumInfo> newFields = new TreeMap<>();
        newFields.put("field4", new LTVarcharInfo(25));
        newFields.put("field5", new LTIntegerInfo());

        schema.addFields(newFields);

        Assertions.assertEquals(schema.getSize(), 128);

        Assertions.assertEquals(schema.getFields(), Set.of("field1", "field2", "field3", "field4", "field5"));
    }
}
