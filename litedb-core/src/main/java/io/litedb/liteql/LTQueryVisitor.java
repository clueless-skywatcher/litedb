package io.litedb.liteql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.litedb.liteql.LTQLParser.CreateTableQueryContext;
import io.litedb.liteql.LTQLParser.DdlStatementContext;
import io.litedb.liteql.LTQLParser.DeleteQueryContext;
import io.litedb.liteql.LTQLParser.DmlStatementContext;
import io.litedb.liteql.LTQLParser.DqlStatementContext;
import io.litedb.liteql.LTQLParser.FieldDefsContext;
import io.litedb.liteql.LTQLParser.FieldNamesContext;
import io.litedb.liteql.LTQLParser.FilterContext;
import io.litedb.liteql.LTQLParser.InsertQueryContext;
import io.litedb.liteql.LTQLParser.ProjectionContext;
import io.litedb.liteql.LTQLParser.QueryContext;
import io.litedb.liteql.LTQLParser.RootContext;
import io.litedb.liteql.LTQLParser.SelectQueryContext;
import io.litedb.liteql.LTQLParser.StatementContext;
import io.litedb.liteql.LTQLParser.UpdateQueryContext;
import io.litedb.liteql.statements.LTCreateTableStatement;
import io.litedb.liteql.statements.LTDeleteFromTableStatement;
import io.litedb.liteql.statements.LTInsertIntoTableStatement;
import io.litedb.liteql.statements.LTStatement;
import io.litedb.liteql.statements.LTSelectFromTableStatement;
import io.litedb.liteql.statements.LTUpdateTableStatement;
import io.litedb.tuples.data.info.LTTupleDatumInfo;

public class LTQueryVisitor extends LTQLBaseVisitor<Object> {
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
        return new LTInsertIntoTableStatement(tableName, fields, row);
    }

    @Override
    public Object visitDeleteQuery(DeleteQueryContext ctx) {
        String tableName = ctx.tableName.getText();

        if (ctx.filter() != null) {
            List<LTPredicate> predicates = visitFilter(ctx.filter());
            return new LTDeleteFromTableStatement(tableName, predicates);
        }

        return new LTDeleteFromTableStatement(tableName);
    }

    @Override
    public LTStatement visitSelectQuery(SelectQueryContext ctx) {
        List<String> projection = visitProjection(ctx.projection());
        
        List<String> tableNames = new ArrayList<>();
        for (int i = 0; i < ctx.tableNames().identifier().size(); i++) {
            tableNames.add(ctx.tableNames().identifier(i).getText());
        }

        Set<String> tableNameSet = new HashSet<>(tableNames);
        if (tableNameSet.size() < tableNames.size()) {
            throw new RuntimeException("Tablenames are repeated in SELECT statement");
        }

        if (tableNames.size() == 1) {
            String tableName = tableNames.get(0);
            if (ctx.filter() != null) {
                List<LTPredicate> predicates = visitFilter(ctx.filter());
                return new LTSelectFromTableStatement(List.of(tableName), projection, predicates);
            }
    
            return new LTSelectFromTableStatement(List.of(tableName), projection);
        }

        if (ctx.filter() != null) {
            List<LTPredicate> predicates = visitFilter(ctx.filter());
            return new LTSelectFromTableStatement(tableNames, projection, predicates);
        }
        return new LTSelectFromTableStatement(tableNames, projection);
    }

    @Override
    public List<LTPredicate> visitFilter(FilterContext ctx) {
        List<LTPredicate> predicates = new ArrayList<>();

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

            predicates.add(new LTPredicate(fieldName, value));
        }

        return predicates;
    }

    @Override
    public Object visitCreateTableQuery(CreateTableQueryContext ctx) {
        String tableName = ctx.identifier().getText();
        Map<String, LTTupleDatumInfo> fields = new HashMap<>();
        for (FieldDefsContext field : ctx.fieldDefs()) {
            String fieldName = field.identifier().getText();
            LTTupleDatumInfo fieldType = LTTupleDatumInfo.inferTypeFromString(field.fieldType().getText());
            fields.put(fieldName, fieldType);
        }

        return new LTCreateTableStatement(tableName, fields);
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
    public LTUpdateTableStatement visitUpdateQuery(UpdateQueryContext ctx) {
        String tableName = ctx.tableName.getText();

        Map<String, String> updateColumns = new HashMap<>();

        for (int i = 0; i < ctx.updateColumn().size(); i++) {
            String fieldName = ctx.updateColumn(i).fieldName.getText();
            String value = ctx.updateColumn(i).value().getText();
            updateColumns.put(fieldName, value);
        }

        if (ctx.filter() != null) {
            List<LTPredicate> predicates = visitFilter(ctx.filter());
            return new LTUpdateTableStatement(tableName, updateColumns, predicates);
        }

        return new LTUpdateTableStatement(tableName, updateColumns);
    }
}
