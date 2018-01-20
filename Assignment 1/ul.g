grammar ul;

options {
    backtrack=true;
}

@header
{
    import ast.*;
    import type.*;
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

program returns [Program p]
@init
{
    p = new Program();
}
    : (f = function { p.addElement(f); })+ EOF
    ;

function returns [Function f]
    :    fd = functionDeclaration fb = functionBody
    { f = new Function(fd, fb); }
    ;

functionDeclaration returns [FunctionDeclaration fd]
    :    tn = compoundType identifier OPENPARENTHESIS formalParameters CLOSEPARENTHESIS
    { fd = new FunctionDeclaration(tn); }
    ;

formalParameters returns [FormalParameters args]
    :    compoundType identifier (COMMA compoundType identifier)*
    |
    ;

functionBody returns [FunctionBody fb]
    :    OPENBRACE variableDeclaration* statement* CLOSEBRACE
    ;

variableDeclaration
    : compoundType identifier SEMICOLON
    ;

compoundType returns [TypeNode tn]
    :    t = type
    { tn = new TypeNode(t); }
    |    type OPENBRACKET INTEGERCONSTANT CLOSEBRACKET
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

identifier returns [Identifier name]
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

type returns [Type t]
    :    intt
    { t = new IntegerType(); }
    |    floatt
    { t = new FloatType(); }
    |    charr
    { t = new CharType(); }
    |    stringg
    { t = new StringType(); }
    |    booleann
    { t = new BooleanType(); }
    |    voidd
    { t = new VoidType(); }
    ;

intt returns [IntegerType i]
    :    INT
    ;

floatt
    :    FLOAT
    ;

charr
    :    CHAR
    ;

stringg
    :    STRING
    ;

booleann
    :    BOOLEAN
    ;

voidd
    :    VOID
    ;

INT              :    'int';

FLOAT            :    'float';

CHAR             :    'char';

STRING           :    'string';

BOOLEAN          :    'boolean';

VOID             :    'void';

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
