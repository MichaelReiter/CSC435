grammar ul;

options {
    backtrack=true;
}

@members {
    protected void mismatch(IntStream input, int ttype, BitSet follow) throws RecognitionException {
        throw new MismatchedTokenException(ttype, input);
    }

    public void recoverFromMismatchedSet(IntStream input, RecognitionException re, BitSet follow) throws RecognitionException {
        reportError(re);
        throw re;
    }
}

@rulecatch {
    catch (RecognitionException re) {
        reportError(re);
        throw re;
    }
}

program
    :    function+ EOF
    ;

function
    :    functionDeclaration functionBody
    ;

functionDeclaration
    :    compoundType identifier OPENPARENTHESIS formalParameters CLOSEPARENTHESIS
    ;

formalParameters
    :    compoundType identifier (COMMA compoundType identifier)*
    |
    ;

functionBody
    :    OPENBRACE variableDeclaration* statement* CLOSEBRACE
    ;

variableDeclaration
    : compoundType identifier SEMICOLON
    ;

compoundType
    :    TYPE
    |    TYPE OPENBRACKET INTEGERCONSTANT CLOSEBRACKET
    ;

statement
    :    SEMICOLON
    |    expressionStatement
    |    assignmentStatement
    |    arrayAssignmentStatement
    |    whileStatement
    |    returnStatement
    |    printStatement
    |    printlnStatement
    |    ifElseStatement
    |    ifStatement
    ;

expressionStatement
    :    expression SEMICOLON
    ;

assignmentStatement
    :    identifier SINGLEEQUALS expression SEMICOLON
    ;

arrayAssignmentStatement
    :    identifier OPENBRACKET expression CLOSEBRACKET SINGLEEQUALS expression SEMICOLON
    ;

whileStatement
    :    WHILE OPENPARENTHESIS expression CLOSEPARENTHESIS block
    ;

returnStatement
    :    RETURN expression? SEMICOLON
    ;

printStatement
    :    PRINT expression SEMICOLON
    ;

printlnStatement
    :    PRINTLN expression SEMICOLON
    ;

ifElseStatement
    :    IF OPENPARENTHESIS expression CLOSEPARENTHESIS block ELSE block
    ;

ifStatement
    :    IF OPENPARENTHESIS expression CLOSEPARENTHESIS block
    ;

block
    :    OPENBRACE statement* CLOSEBRACE
    ;

atom
    :    identifier
    |    literal
    |    parenthesisExpression
    |    functionCall
    |    arrayReference
    ;

parenthesisExpression
    :    OPENPARENTHESIS expression CLOSEPARENTHESIS
    ;

functionCall
    :    identifier OPENPARENTHESIS expressionList CLOSEPARENTHESIS
    ;

arrayReference
    :    identifier OPENBRACKET expression CLOSEBRACKET
    ;

multiplyExpression
    :    atom (STAR atom)*
    ;

addExpression
    :    multiplyExpression addExpressionPrime
    ;

addExpressionPrime
    :    PLUS multiplyExpression addExpressionPrime
    |    MINUS multiplyExpression addExpressionPrime
    |
    ;

lessThanExpression
    :    addExpression (LESSTHAN addExpression)*
    ;

equalityExpression
    :    lessThanExpression (DOUBLEEQUALS lessThanExpression)*
    ;

expression
    :    equalityExpression
    // |    arrayReference
    // |    functionCall
    ;

expressionList
    :    expression (COMMA expression)*
    |
    ;

identifier
    :    ID
    ;

literal
    :    integerLiteral
    |    floatLiteral
    |    characterLiteral
    |    stringLiteral
    |    booleanLiteral
    ;

integerLiteral
    :    INTEGERCONSTANT
    ;

floatLiteral
    :    FLOATCONSTANT
    ;

characterLiteral
    :    CHARACTERCONSTANT
    ;

stringLiteral
    :    STRINGCONSTANT
    ;

booleanLiteral
    :    TRUE
    |    FALSE
    ;

TYPE
    :    'int'
    |    'float'
    |    'char'
    |    'string'
    |    'boolean'
    |    'void'
    ;

IF               :    'if';

ELSE             :    'else';

WHILE            :    'while';

PRINT            :    'print';

PRINTLN          :    'println';

RETURN           :    'return';

TRUE             :    'true';

FALSE            :    'false';

SEMICOLON        :    ';';

OPENPARENTHESIS  :    '(';

CLOSEPARENTHESIS :    ')';

OPENBRACKET      :    '[';

CLOSEBRACKET     :    ']';

OPENBRACE        :    '{';

CLOSEBRACE       :    '}';

COMMA            :    ',';

SINGLEEQUALS     :    '=';

STAR             :    '*';

PLUS             :    '+';

MINUS            :    '-';

LESSTHAN         :    '<';

DOUBLEEQUALS     :    '==';

INTEGERCONSTANT
    :    ('0'..'9')+
    ;

FLOATCONSTANT
    :    ('0'..'9')+ '.' ('0'..'9')+
    ;

CHARACTERCONSTANT
    :    '\u0027' ( 'a'..'z' | 'A'..'Z' | ' ' | '0'..'9') '\u0027'
    ;

STRINGCONSTANT
    :    '\u0022' ( 'a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '\u0022'
    ;

ID
    :    (( 'a'..'z' | 'A'..'Z') | '_') (( 'a'..'z' | 'A'..'Z') | '_' | '0'..'9')*
    ;

WHITESPACE
    :    ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;}
    ;

COMMENT
    :    '//' ~('\r' | '\n')* {$channel = HIDDEN;}
    ;
