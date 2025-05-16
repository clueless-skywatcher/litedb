package io.litedb.liteql;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.litedb.liteql.statements.LTCreateTableStatement;
import io.litedb.tuples.data.info.LTIntegerInfo;
import io.litedb.tuples.data.info.LTTupleDatumInfo;
import io.litedb.tuples.data.info.LTVarcharInfo;

public class LTCreateTableParseTest {
    @Test
    public void testCreateTable() {
        LTParsingMachine machine = new LTParsingMachine();
        LTCreateTableStatement stmt = (LTCreateTableStatement) machine.parseStatement("create table students (id int, name varchar(200));");
        Assertions.assertEquals(stmt.getTableName(), "students");
        
        Map<String, LTTupleDatumInfo> fields = stmt.getFields();
        Map<String, LTTupleDatumInfo> expectedFields = new HashMap<>();

        expectedFields.put("id", new LTIntegerInfo());
        expectedFields.put("name", new LTVarcharInfo(200));
        
        Assertions.assertIterableEquals(fields.keySet(), expectedFields.keySet());

        for (String key: fields.keySet()) {
            Assertions.assertEquals(fields.get(key), expectedFields.get(key));
        }
    }
}
