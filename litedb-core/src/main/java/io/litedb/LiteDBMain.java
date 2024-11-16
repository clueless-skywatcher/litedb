package io.litedb;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

import io.litedb.liteql.LiteQLParsingMachine;
import io.litedb.liteql.statements.CreateTableStatement;
import io.litedb.liteql.statements.LiteQLStatement;
import io.litedb.liteql.statements.results.CreateTableResult;
import io.litedb.tuples.data.IntegerData;
import io.litedb.tuples.data.VarcharData;
import io.litedb.tuples.data.info.IntegerInfo;
import io.litedb.tuples.data.info.TupleDatumInfo;
import io.litedb.tuples.data.info.VarcharInfo;

public class LiteDBMain {
    public static void main(String[] args) throws IOException {
        Map<String, TupleDatumInfo> fields = new TreeMap<>();
        fields.put("roll_number", new IntegerInfo());
        fields.put("name", new VarcharInfo(200));
        LiteQLParsingMachine machine = new LiteQLParsingMachine();
        
        LiteDB db = new LiteDB(".litedb");
        CreateTableStatement stmt = (CreateTableStatement) machine.parseStatement("create table students (roll_number int, name varchar(200));");
        stmt.execute(db);
        System.out.println(stmt.getResult().toString());
        System.out.println();
        
        db.insertValues("students", Map.ofEntries(
                new AbstractMap.SimpleEntry<>("roll_number", new IntegerData(1)),
                new AbstractMap.SimpleEntry<>("name", new VarcharData("Somi", 200))));
        db.insertValues("students", Map.ofEntries(
                new AbstractMap.SimpleEntry<>("roll_number", new IntegerData(2)),
                new AbstractMap.SimpleEntry<>("name", new VarcharData("Epsi", 200))));
        db.insertValues("students", Map.ofEntries(
                new AbstractMap.SimpleEntry<>("roll_number", new IntegerData(3)),
                new AbstractMap.SimpleEntry<>("name", new VarcharData("Shady", 200))));

        LiteQLStatement statement = machine.parseStatement("select name from students;");
        statement.execute(db);
        System.out.println(statement.getResult().toString());
        System.out.println();

        statement = machine.parseStatement("select table_name, row_size from tables_meta;");
        statement.execute(db);
        System.out.println(statement.getResult());
        System.out.println();

        statement = machine.parseStatement("select * from columns_meta;");
        statement.execute(db);
        System.out.println(statement.getResult());
        System.out.println();
    }
}
