package io.litedb.liteql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;

import io.litedb.liteql.statements.LTStatement;

public class LTParsingMachine {
    public LTStatement parseStatement(String statement) {
        Lexer lexer = new LTQLLexer(new ANTLRInputStream(statement));
        TokenStream stream = new CommonTokenStream(lexer);
        LTQLParser parser = new LTQLParser(stream);
        LTQueryVisitor visitor = new LTQueryVisitor();
        return (LTStatement) visitor.visit(parser.root());
    }
}
