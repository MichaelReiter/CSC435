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
    :    compoundType IDENTIFIER OPENPARENTHESIS formalParameters CLOSEPARENTHESIS
    ;

formalParameters
    :    compoundType IDENTIFIER moreFormals*
    |
    ;

moreFormals
    :    COMMA compoundType IDENTIFIER
    ;

functionBody
    :    OPENBRACE variableDeclaration* statement* CLOSEBRACE
    ;

variableDeclaration
    : compoundType IDENTIFIER SEMICOLON
    ;

compoundType
    :    TYPE
    |    TYPE OPENBRACKET INTEGERCONSTANT CLOSEBRACKET
    ;

statement
    :    SEMICOLON
    |    expression SEMICOLON
    |    IDENTIFIER SINGLEEQUALS expression SEMICOLON
    |    IDENTIFIER OPENBRACKET expression CLOSEBRACKET SINGLEEQUALS expression SEMICOLON
    |    WHILE OPENPARENTHESIS expression CLOSEPARENTHESIS block
    |    RETURN expression? SEMICOLON
    |    PRINT expression SEMICOLON
    |    PRINTLN expression SEMICOLON
    |    IF OPENPARENTHESIS expression CLOSEPARENTHESIS block ELSE block
    |    IF OPENPARENTHESIS expression CLOSEPARENTHESIS block
    ;

block
    :    OPENBRACE statement* CLOSEBRACE
    ;

primaryExpression
    :    IDENTIFIER
    |    literal
    |    OPENPARENTHESIS expression CLOSEPARENTHESIS
    |    IDENTIFIER OPENPARENTHESIS expressionList CLOSEPARENTHESIS
    |    IDENTIFIER OPENBRACKET expression CLOSEBRACKET
    ;

multiplyExpression
    :    primaryExpression multiplyExpressionPrime
    ;

multiplyExpressionPrime
    :    STAR primaryExpression multiplyExpressionPrime
    |
    ;

additionExpression
    :    multiplyExpression additionExpressionPrime
    ;

additionExpressionPrime
    :    PLUS multiplyExpression additionExpressionPrime
    |    MINUS multiplyExpression additionExpressionPrime
    |
    ;

lessThanExpression
    :    additionExpression lessThanExpressionPrime
    ;

lessThanExpressionPrime
    :    LESSTHAN additionExpression lessThanExpressionPrime
    |
    ;

equalityExpression
    :    lessThanExpression equalityExpressionPrime
    ;

equalityExpressionPrime
    :    DOUBLEEQUALS lessThanExpression equalityExpressionPrime
    |
    ;

expression
    :    equalityExpression
    |    IDENTIFIER OPENBRACKET expression CLOSEBRACKET
    |    IDENTIFIER OPENPARENTHESIS expressionList CLOSEPARENTHESIS
    ;

expressionList
    :    expression expressionMore*
    |
    ;

expressionMore
    :    COMMA expression
    ;

literal
    :    INTEGERCONSTANT
    |    FLOATCONSTANT
    |    CHARACTERCONSTANT
    |    STRINGCONSTANT
    |    TRUE
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

IDENTIFIER
    :    (( 'a'..'z' | 'A'..'Z') | '_') (( 'a'..'z' | 'A'..'Z') | '_' | '0'..'9')*
    ;

WHITESPACE
    :    ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;}
    ;

COMMENT
    :    '//' ~('\r' | '\n')* {$channel = HIDDEN;}
    ;
