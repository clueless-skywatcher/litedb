grammar LTQL;

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
    | updateQuery
    | deleteQuery
    ;

selectQuery
    : SELECT projection FROM tableNames filter?
    ;

tableNames
    : identifier (',' identifier)*
    ;

filter
    : WHERE predicate (AND predicate)?
    ;

predicate
    : fieldName=identifier '=' value
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

updateQuery
    : UPDATE tableName=identifier SET updateColumn (',' updateColumn)*
        filter?
    ;

deleteQuery
    : DELETE FROM tableName=identifier filter?
    ;

updateColumn
    : fieldName=identifier '=' value
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

AND: 'and';
CREATE: 'create';
DELETE: 'delete';
DROP: 'drop';
FROM: 'from';
IF: 'if';
INSERT: 'insert';
INTO: 'into';
SELECT: 'select';
SET: 'set';
TABLE: 'table';
UPDATE: 'update';
VALUES: 'values';
WHERE: 'where';
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
