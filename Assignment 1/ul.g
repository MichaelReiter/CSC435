grammar ul;

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
        :    compoundType IDENTIFIER LEFTPARENTHESIS formalParameters RIGHTPARENTHESIS
        ;

formalParameters
        :    compoundType IDENTIFIER moreFormals*
        ;

moreFormals
        :    COMMA compoundType IDENTIFIER
        ;

functionBody
        :    LEFTBRACE variableDeclaration* statement* RIGHTBRACE
        ;

variableDeclaration
        :    compoundType IDENTIFIER SEMICOLON
        ;

compoundType
        :    type
        |    type LEFTBRACKET integerConstant RIGHTBRACKET
        ;

type
        :    INT
        |    FLOAT
        |    CHAR
        |    STRING
        |    BOOLEAN
        |    VOID
        ;

statement
        :    SEMICOLON
        |    expression SEMICOLON
        |    IF LEFTPARENTHESIS expression RIGHTPARENTHESIS block
        |    IF LEFTPARENTHESIS expression RIGHTPARENTHESIS block ELSE block
        |    WHILE LEFTPARENTHESIS expression RIGHTPARENTHESIS block
        |    PRINT expression SEMICOLON
        |    PRINTLN expression SEMICOLON
        |    RETURN expression? SEMICOLON
        |    IDENTIFIER SINGLEEQUALS expression SEMICOLON
        |    IDENTIFIER LEFTBRACKET expression RIGHTBRACKET SINGLEEQUALS expression SEMICOLON
        ;

block
        :    LEFTBRACE statement* RIGHTBRACE
        ;

expression
        :    expression OPERATOR expression
        |    IDENTIFIER LEFTBRACKET expression RIGHTBRACKET
        |    IDENTIFIER LEFTPARENTHESIS expressionList RIGHTPARENTHESIS
        |    IDENTIFIER
        |    literal
        |    LEFTPARENTHESIS expression RIGHTPARENTHESIS
        ;

literal
        :    stringConstant
        |    integerConstant
        |    floatConstant
        |    characterConstant
        |    TRUE
        |    FALSE
        ;

expressionList
        :    expression expressionMore*
        ;

expressionMore
        :    COMMA expression
        ;

integerConstant
        :    DIGIT+
        ;

stringConstant
        :    DOUBLEQUOTE CHARACTER* DOUBLEQUOTE
        ;

characterConstant
        :    SINGLEQUOTE CHARACTER SINGLEQUOTE
        ;

floatConstant
        :    DIGIT+ DECIMALPOINT DIGIT+
        ;

IDENTIFIER
        :    (UNDERSCORE | LETTER) (UNDERSCORE | LETTER | DIGIT)*
        ;

CHARACTER
        :    LETTER
        |    DIGIT
        |    UNDERSCORE
        |    ' '
        ;

OPERATOR
        :    DOUBLEEQUALS
        |    LESSTHAN
        |    PLUS
        |    MINUS
        |    MULTIPLY
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

LEFTPARENTHESIS  :    '(';

RIGHTPARENTHESIS :    ')';

COMMA            :    ',';

LEFTBRACE        :    '{';

RIGHTBRACE       :    '}';

SEMICOLON        :    ';';

LEFTBRACKET      :    '[';

RIGHTBRACKET     :    ']';

SINGLEQUOTE      :    '\'';

DOUBLEQUOTE      :    '"';

DECIMALPOINT     :    '.';

UNDERSCORE       :    '_';

SINGLEEQUALS     :    '=';

DOUBLEEQUALS     :    '==';

LESSTHAN         :    '<';

PLUS             :    '+';

MINUS            :    '-';

MULTIPLY         :    '*';

LETTER
        :    'a'..'z'
        |    'A'..'Z'
        ;

DIGIT
        :    '0'..'9'
        ;

WHITESPACE
        :    ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;}
        ;

COMMENT
        :    '//' ~('\r' | '\n')* ('\r' | '\n') {$channel = HIDDEN;}
        ;
