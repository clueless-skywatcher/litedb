package io.litedb.liteql;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.litedb.liteql.statements.CreateTableStatement;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class LiteQLCreateTableParseTest {
    @Test
    public void testCreateTable() {
        LiteQLParsingMachine machine = new LiteQLParsingMachine();
        CreateTableStatement stmt = (CreateTableStatement) machine.parseStatement("create table students (id int, name varchar(200));");
        Assertions.assertEquals(stmt.getTableName(), "students");
        
        Map<String, TupleDatumInfo> fields = stmt.getFields();
        Map<String, TupleDatumInfo> expectedFields = new HashMap<>();

        expectedFields.put("id", new IntegerInfo());
        expectedFields.put("name", new VarcharInfo(200));
        
        Assertions.assertIterableEquals(fields.keySet(), expectedFields.keySet());

        for (String key: fields.keySet()) {
            Assertions.assertEquals(fields.get(key), expectedFields.get(key));
        }
    }
}
