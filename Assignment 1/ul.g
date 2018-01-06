grammar ul;

// options {
//     backtracking=true
// }

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
        :    compoundType identifier '(' formalParameters ')'
        ;

formalParameters
        :    compoundType identifier moreFormals*
        |
        ;

moreFormals
        :    ',' compoundType identifier
        ;

functionBody
        :    '{' variableDeclaration* statement* '}'
        ;

variableDeclaration
        : compoundType identifier ';'
        ;

compoundType
        :    TYPE
        // |    TYPE '[' INTEGERCONSTANT ']'
        ;

TYPE
        :    'int'
        |    'float'
        |    'char'
        |    'string'
        |    'boolean'
        |    'void'
        ;

statement
        :    ';'
        // |    expression ';'
        // |    IF '(' expression ')' block ELSE block
        // |    IF '(' expression ')' block
        // |    WHILE '(' expression ')' block
        // |    identifier '=' expression ';'
        ;

block
        :    '{' statement* '}'
        ;

expression
        :    identifier
        // |    literal
        // |    expression OPERATOR expression
        // |    identifier '(' expressionList ')'
        // |    '(' expression ')'
        ;

// literal
//         :    stringConstant
//         |    INTEGERCONSTANT
//         |    floatConstant
//         |    characterConstant
//         |    TRUE
//         |    FALSE
//         ;

// expressionList
//         :    expression expressionMore*
//         |
//         ;

// expressionMore
//         :    ',' expression
//         ;

INTEGERCONSTANT
        :    ('0'..'9')+
        ;

// stringConstant
//         :    '"' CHARACTER* '"'
//         ;

// characterConstant
//         :    '\'' CHARACTER '\''
//         ;

// floatConstant
//         :    '0'..'9'+ '.' '0'..'9'+
//         ;

// CHARACTER
//         :    LETTER
//         |    '0'..'9'
//         |    ' '
//         ;

identifier
        :    ('_' | LETTER) ('_' | LETTER | '0'..'9')*
        ;

LETTER
        :    'a'..'z'
        |    'A'..'Z'
        ;

IF               :    'if';

ELSE             :    'else';

WHILE            :    'while';

PRINT            :    'print';

PRINTLN          :    'println';

RETURN           :    'return';

TRUE             :    'true';

FALSE            :    'false';

OPERATOR
        :    '=='
        |    '<'
        |    '+'
        |    '-'
        |    '*'
        ;

WHITESPACE
        :    ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;}
        ;

COMMENT
        :    '//' ~('\r' | '\n')* ('\r' | '\n') {$channel = HIDDEN;}
        ;
