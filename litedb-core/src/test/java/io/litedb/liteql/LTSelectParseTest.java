package io.litedb.liteql;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.litedb.liteql.statements.LTSelectFromTableStatement;

public class LTSelectParseTest {
    @Test
    public void testSelectQuery() {
        LTParsingMachine machine = new LTParsingMachine();
        LTSelectFromTableStatement stmt = (LTSelectFromTableStatement) machine.parseStatement("select id, name from table1;");
        Assertions.assertEquals(stmt.getTableNames(), List.of("table1"));
        Assertions.assertIterableEquals(stmt.getFields(), List.of("id", "name"));
        LTSelectFromTableStatement stmt2 = (LTSelectFromTableStatement) machine.parseStatement("select id,name from table1;");
        Assertions.assertEquals(stmt2.getTableNames(), List.of("table1"));
        Assertions.assertIterableEquals(stmt2.getFields(), List.of("id", "name"));
        LTSelectFromTableStatement stmt3 = (LTSelectFromTableStatement) machine.parseStatement("select * from table1");
        Assertions.assertEquals(stmt3.getTableNames(), List.of("table1"));
        Assertions.assertIterableEquals(stmt3.getFields(), List.of());
    }
}
