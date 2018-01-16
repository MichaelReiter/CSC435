grammar ulNoActions;

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
    :    compoundType IDENTIFIER '(' formalParameters ')'
    ;

formalParameters
    :    compoundType IDENTIFIER moreFormals*
    |
    ;

moreFormals
    :    ',' compoundType IDENTIFIER
    ;

functionBody
    :    '{' variableDeclaration* statement* '}'
    ;

variableDeclaration
    : compoundType IDENTIFIER ';'
    ;

compoundType
    :    TYPE
    |    TYPE '[' INTEGERCONSTANT ']'
    ;

statement
    :    ';'
    |    expression ';'
    |    IDENTIFIER '=' expression ';'
    |    IDENTIFIER '[' expression ']' '=' expression ';'
    |    'while' '(' expression ')' block
    |    'return' expression? ';'
    |    'print' expression ';'
    |    'println' expression ';'
    |    'if' '(' expression ')' block 'else' block
    |    'if' '(' expression ')' block
    ;

block
    :    '{' statement* '}'
    ;

primaryExpression
    :    IDENTIFIER
    |    literal
    |    '(' expression ')'
    |    IDENTIFIER '(' expressionList ')'
    |    IDENTIFIER '[' expression ']'
    ;

multiplyExpression
    :    primaryExpression multiplyExpressionPrime
    ;

multiplyExpressionPrime
    :    '*' primaryExpression multiplyExpressionPrime
    |
    ;

additionExpression
    :    multiplyExpression additionExpressionPrime
    ;

additionExpressionPrime
    :    '+' multiplyExpression additionExpressionPrime
    |    '-' multiplyExpression additionExpressionPrime
    |
    ;

lessThanExpression
    :    additionExpression lessThanExpressionPrime
    ;

lessThanExpressionPrime
    :    '<' additionExpression lessThanExpressionPrime
    |
    ;

equalityExpression
    :    lessThanExpression equalityExpressionPrime
    ;

equalityExpressionPrime
    :    '==' lessThanExpression equalityExpressionPrime
    |
    ;

expression
    :    equalityExpression
    |    IDENTIFIER '[' expression ']'
    |    IDENTIFIER '(' expressionList ')'
    ;

expressionList
    :    expression expressionMore*
    |
    ;

expressionMore
    :    ',' expression
    ;

literal
    :    INTEGERCONSTANT
    |    FLOATCONSTANT
    |    CHARACTERCONSTANT
    |    STRINGCONSTANT
    |    'true'
    |    'false'
    ;

TYPE
    :    'int'
    |    'float'
    |    'char'
    |    'string'
    |    'boolean'
    |    'void'
    ;

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

IDENTIFIER
    :    (( 'a'..'z' | 'A'..'Z') | '_') (( 'a'..'z' | 'A'..'Z') | '_' | '0'..'9')*
    ;

WHITESPACE
    :    ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;}
    ;

COMMENT
    :    '//' ~('\r' | '\n')* {$channel = HIDDEN;}
    ;
