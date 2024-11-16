grammar LiteQuery;

root: statement EOF;

statement: query SEMICOLON;

query
    : dqlStatement 
    | ddlStatement
    | dmlStatement
    ;

dqlStatement
    : selectQuery
    ;

ddlStatement
    : createTableQuery
    | dropTableQuery
    ;

dmlStatement
    : insertQuery
    ;

selectQuery
    : SELECT projection FROM tableName=identifier
    ;

createTableQuery
    : CREATE TABLE tableName=identifier '(' fieldDefs (',' fieldDefs)* ')'
    ;

dropTableQuery
    : DROP TABLE tableName=identifier
    ;

insertQuery
    : INSERT INTO tableName=identifier 
        '(' fieldNames ')'
        VALUES '(' value (',' value)* ')'
    ;

value
    : INTEGER
    | STRING
    | BOOLEAN_VALUE
    ;

fieldNames
    : identifier (',' identifier)*
    ;

fieldDefs
    : fieldName=identifier fieldType
    ;

fieldType
    : typeName=identifier ('(' maxLength=INTEGER ')')?
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

BOOLEAN_VALUE: 'true' | 'false';

CREATE: 'create';
DROP: 'drop';
FROM: 'from';
IF: 'if';
INSERT: 'insert';
INTO: 'into';
SELECT: 'select';
TABLE: 'table';
VALUES: 'values';
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
