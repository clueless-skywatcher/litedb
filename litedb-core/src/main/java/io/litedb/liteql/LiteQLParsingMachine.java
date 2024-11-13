package io.litedb.liteql;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;

import io.litedb.liteql.statements.LiteQLStatement;

public class LiteQLParsingMachine {
    public LiteQLStatement parseStatement(String statement) {
        Lexer lexer = new LiteQueryLexer(new ANTLRInputStream(statement));
        TokenStream stream = new CommonTokenStream(lexer);
        LiteQueryParser parser = new LiteQueryParser(stream);
        LiteQLVisitor visitor = new LiteQLVisitor();
        return (LiteQLStatement) visitor.visit(parser.root());
    }
}
