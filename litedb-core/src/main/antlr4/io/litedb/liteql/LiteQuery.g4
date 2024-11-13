grammar LiteQuery;

root: statement EOF;

statement: query SEMICOLON;

query
    : dqlStatement 
    | ddlStatement
    ;

dqlStatement
    : selectQuery
    ;

ddlStatement
    : createTableQuery
    ;

selectQuery
    : SELECT projection FROM tableName=identifier
    ;

createTableQuery
    : CREATE TABLE tableName=identifier '(' fieldDefs (',' fieldDefs) ')'
    ;

fieldDefs
    : fieldName=identifier fieldType
    ;

fieldType
    : typeName=identifier ('(' maxLength=INTEGER ')')
    ;

projection
    : identifier (',' identifier)*
    | ASTERISK
    ;

identifier
    : IDENTIFIER
    ;

fragment LETTERS: [a-zA-Z]+;
fragment DIGITS: [0-9]+;

BOOLEAN: 'boolean';
CREATE: 'create';
FROM: 'from';
IF: 'if';
INSERT: 'insert';
INT: 'int';
INTO: 'into';
SELECT: 'select';
TABLE: 'table';
VALUES: 'values';
VARCHAR: 'varchar';
WITH: 'with';

EQ  : '=';
NEQ : '<>' | '!=';
LT  : '<';
LTE : '<=';
GT  : '>';
GTE : '>=';

PLUS: '+';
MINUS: '-';
ASTERISK: '*';
SLASH: '/';
PERCENT: '%';
CONCAT: '||';
SEMICOLON: ';';

STRING
    : '\'' ( ~'\'' | '\'\'' )* '\''
    ;

INTEGER
    : DIGITS
    ;

IDENTIFIER
    : (LETTERS | '_')(LETTERS | DIGITS | '_')*
    ;

QUOTED_IDENTIFIER
    : '"'( ~'"' | '""' )*'"'
    ;

WS
    : [ \t\r\n]+ -> skip
    ;

COMMENT
    : '--' ~[\r\n]* '\r'? '\n'? -> channel(HIDDEN)
    ;
