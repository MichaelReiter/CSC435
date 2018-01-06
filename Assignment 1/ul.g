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
        :    function+
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
        |    'if' '(' expression ')' block 'else' block
        |    'if' '(' expression ')' block
        |    'while' '(' expression ')' block
        |    'print' expression ';'
        |    'println' expression ';'
        |    'return' expression ';'
        |    IDENTIFIER '=' expression ';'
        |    IDENTIFIER '[' expression ']' '=' expression ';'
        ;

block
        :    '{' statement* '}'
        ;

expression
        :    literal
        |    IDENTIFIER
        |    IDENTIFIER '[' expression ']'
        |    IDENTIFIER '(' expressionList ')'
        |    '(' expression ')'
        //   TODO expression operator expression
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
        :    '\'' ( 'a'..'z' | 'A'..'Z' | ' ' | '0'..'9') '\''
        ;

STRINGCONSTANT
        :    '"' ( 'a'..'z' | 'A'..'Z' | ' ' | '0'..'9')+ '"'
        ;

IDENTIFIER
        :    (( 'a'..'z' | 'A'..'Z') | '_') (( 'a'..'z' | 'A'..'Z') | '_' | '0'..'9')*
        ;

WHITESPACE
        :    ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;}
        ;

COMMENT
        :    '//' ~('\r' | '\n')* ('\r' | '\n') {$channel = HIDDEN;}
        ;
