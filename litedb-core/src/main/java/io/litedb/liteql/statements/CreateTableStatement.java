package io.litedb.liteql.statements;

import java.util.Map;

import io.litedb.LiteDB;
import io.litedb.liteql.statements.results.CreateTableResult;
import io.litedb.liteql.statements.results.LiteQLResult;
import io.litedb.planning.DBPlan;
import io.litedb.tuples.data.info.TupleDatumInfo;
import lombok.Getter;

public class CreateTableStatement implements LiteQLStatement {
    private @Getter String tableName;
    private @Getter Map<String, TupleDatumInfo> fields;
    private @Getter long timeTaken;
    private @Getter LiteQLResult result;

    public CreateTableStatement(String tableName, Map<String, TupleDatumInfo> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }

    @Override
    public void execute(LiteDB db) {
        long timeTaken = System.currentTimeMillis();
        db.createTable(tableName, fields);
        CreateTableResult result = new CreateTableResult(tableName);
        result.setTimeTaken(System.currentTimeMillis() - timeTaken);
        this.result = result;
    }

    @Override
    public boolean isDDL() {
        return true;
    }

    @Override
    public boolean isDML() {
        return false;
    }

    @Override
    public boolean isDQL() {
        return false;
    }

    @Override
    public void execute(LiteDB db, DBPlan plan) {
        throw new UnsupportedOperationException("Plans not supported for table, view or index creation");
    }    
}
