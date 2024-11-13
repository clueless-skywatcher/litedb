package io.litedb.liteql;

import java.util.ArrayList;
import java.util.List;

import io.litedb.liteql.LiteQueryParser.CreateTableQueryContext;
import io.litedb.liteql.LiteQueryParser.DdlStatementContext;
import io.litedb.liteql.LiteQueryParser.DqlStatementContext;
import io.litedb.liteql.LiteQueryParser.ProjectionContext;
import io.litedb.liteql.LiteQueryParser.QueryContext;
import io.litedb.liteql.LiteQueryParser.RootContext;
import io.litedb.liteql.LiteQueryParser.SelectQueryContext;
import io.litedb.liteql.LiteQueryParser.StatementContext;
import io.litedb.liteql.statements.SelectFromTableStatement;

public class LiteQLVisitor extends LiteQueryBaseVisitor<Object> {
    
    @Override
    public List<String> visitProjection(ProjectionContext ctx) {
        if (ctx.ASTERISK() != null) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < ctx.identifier().size(); i++) {
            result.add(ctx.identifier(i).getText());
        }

        return result;
    }

    @Override
    public SelectFromTableStatement visitSelectQuery(SelectQueryContext ctx) {
        List<String> projection = visitProjection(ctx.projection());
        String tableName = ctx.tableName.getText();
        return new SelectFromTableStatement(tableName, projection);
    }

    @Override
    public Object visitCreateTableQuery(CreateTableQueryContext ctx) {
        // TODO Auto-generated method stub
        return super.visitCreateTableQuery(ctx);
    }

    @Override
    public Object visitDdlStatement(DdlStatementContext ctx) {
        return visitCreateTableQuery(ctx.createTableQuery());
    }

    @Override
    public Object visitDqlStatement(DqlStatementContext ctx) {
        return visitSelectQuery(ctx.selectQuery());
    }

    @Override
    public Object visitQuery(QueryContext ctx) {
        if (ctx.ddlStatement() != null) {
            return visitDdlStatement(ctx.ddlStatement());
        } else {
            return visitDqlStatement(ctx.dqlStatement());
        }
    }

    @Override
    public Object visitRoot(RootContext ctx) {
        return visitStatement(ctx.statement());
    }

    @Override
    public Object visitStatement(StatementContext ctx) {
        return visitQuery(ctx.query());
    }    
}
