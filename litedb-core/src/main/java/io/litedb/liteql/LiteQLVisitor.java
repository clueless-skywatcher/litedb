package io.litedb.liteql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.litedb.liteql.LiteQueryParser.CreateTableQueryContext;
import io.litedb.liteql.LiteQueryParser.DdlStatementContext;
import io.litedb.liteql.LiteQueryParser.DeleteQueryContext;
import io.litedb.liteql.LiteQueryParser.DmlStatementContext;
import io.litedb.liteql.LiteQueryParser.DqlStatementContext;
import io.litedb.liteql.LiteQueryParser.FieldDefsContext;
import io.litedb.liteql.LiteQueryParser.FieldNamesContext;
import io.litedb.liteql.LiteQueryParser.FilterContext;
import io.litedb.liteql.LiteQueryParser.InsertQueryContext;
import io.litedb.liteql.LiteQueryParser.ProjectionContext;
import io.litedb.liteql.LiteQueryParser.QueryContext;
import io.litedb.liteql.LiteQueryParser.RootContext;
import io.litedb.liteql.LiteQueryParser.SelectQueryContext;
import io.litedb.liteql.LiteQueryParser.StatementContext;
import io.litedb.liteql.LiteQueryParser.UpdateQueryContext;
import io.litedb.liteql.statements.CreateTableStatement;
import io.litedb.liteql.statements.DeleteFromTableStatement;
import io.litedb.liteql.statements.InsertIntoTableStatement;
import io.litedb.liteql.statements.SelectFromTableStatement;
import io.litedb.liteql.statements.UpdateTableStatement;
import io.litedb.tuples.data.info.TupleDatumInfo;

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
    public Object visitDmlStatement(DmlStatementContext ctx) {
        if (ctx.updateQuery() != null) {
            return visitUpdateQuery(ctx.updateQuery());
        } else if (ctx.deleteQuery() != null) {
            return visitDeleteQuery(ctx.deleteQuery());
        }
        return visitInsertQuery(ctx.insertQuery());
    }

    @Override
    public Object visitInsertQuery(InsertQueryContext ctx) {
        String tableName = ctx.tableName.getText();

        List<String> fields = visitFieldNames(ctx.fieldNames());

        List<String> row = new ArrayList<>();

        for (int i = 0; i < ctx.value().size(); i++) {
            String val = ctx.value(i).getText();
            if (val.startsWith("\'") && val.endsWith("\'")) {
                val = val.substring(1, val.length() - 1);
            }
            row.add(val);
        }
        return new InsertIntoTableStatement(tableName, fields, row);
    }

    @Override
    public Object visitDeleteQuery(DeleteQueryContext ctx) {
        String tableName = ctx.tableName.getText();

        if (ctx.filter() != null) {
            List<QueryPredicate> predicates = visitFilter(ctx.filter());
            return new DeleteFromTableStatement(tableName, predicates);
        }

        return new DeleteFromTableStatement(tableName);
    }

    @Override
    public SelectFromTableStatement visitSelectQuery(SelectQueryContext ctx) {
        List<String> projection = visitProjection(ctx.projection());
        String tableName = ctx.tableName.getText();

        if (ctx.filter() != null) {
            List<QueryPredicate> predicates = visitFilter(ctx.filter());
            return new SelectFromTableStatement(tableName, projection, predicates);
        }

        return new SelectFromTableStatement(tableName, projection);
    }

    @Override
    public List<QueryPredicate> visitFilter(FilterContext ctx) {
        List<QueryPredicate> predicates = new ArrayList<>();

        for (int i = 0; i < ctx.predicate().size(); i++) {
            String fieldName = ctx.predicate(i).fieldName.getText();
            Object value;

            if (ctx.predicate(i).value().INTEGER() != null) {
                value = Integer.parseInt(ctx.predicate(i).value().getText());
            } else if (ctx.predicate(i).value().BOOLEAN_VALUE() != null) {
                value = Boolean.parseBoolean(ctx.predicate(i).value().getText());
            } else {
                value = ctx.predicate(i).value().getText();
            }

            predicates.add(new QueryPredicate(fieldName, value));
        }

        return predicates;
    }

    @Override
    public Object visitCreateTableQuery(CreateTableQueryContext ctx) {
        String tableName = ctx.identifier().getText();
        Map<String, TupleDatumInfo> fields = new HashMap<>();
        for (FieldDefsContext field : ctx.fieldDefs()) {
            String fieldName = field.identifier().getText();
            TupleDatumInfo fieldType = TupleDatumInfo.inferTypeFromString(field.fieldType().getText());
            fields.put(fieldName, fieldType);
        }

        return new CreateTableStatement(tableName, fields);
    }

    @Override
    public Object visitDdlStatement(DdlStatementContext ctx) {
        if (ctx.dropTableQuery() != null) {
            return visitDropTableQuery(ctx.dropTableQuery());
        } else {
            return visitCreateTableQuery(ctx.createTableQuery());
        }
    }

    @Override
    public Object visitDqlStatement(DqlStatementContext ctx) {
        return visitSelectQuery(ctx.selectQuery());
    }

    @Override
    public Object visitQuery(QueryContext ctx) {
        if (ctx.ddlStatement() != null) {
            return visitDdlStatement(ctx.ddlStatement());
        } else if (ctx.dmlStatement() != null) {
            return visitDmlStatement(ctx.dmlStatement());
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

    @Override
    public List<String> visitFieldNames(FieldNamesContext ctx) {
        List<String> fields = new ArrayList<>();

        for (int i = 0; i < ctx.identifier().size(); i++) {
            fields.add(ctx.identifier(i).getText());
        }

        return fields;
    }

    @Override
    public UpdateTableStatement visitUpdateQuery(UpdateQueryContext ctx) {
        String tableName = ctx.tableName.getText();

        Map<String, String> updateColumns = new HashMap<>();

        for (int i = 0; i < ctx.updateColumn().size(); i++) {
            String fieldName = ctx.updateColumn(i).fieldName.getText();
            String value = ctx.updateColumn(i).value().getText();
            updateColumns.put(fieldName, value);
        }

        if (ctx.filter() != null) {
            List<QueryPredicate> predicates = visitFilter(ctx.filter());
            return new UpdateTableStatement(tableName, updateColumns, predicates);
        }

        return new UpdateTableStatement(tableName, updateColumns);
    }
}
