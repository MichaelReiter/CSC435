lexer grammar ul;

// $ANTLR src "ul.g" 206
TYPE
    :    'int'
    |    'float'
    |    'char'
    |    'string'
    |    'boolean'
    |    'void'
    ;

// $ANTLR src "ul.g" 215
IF               :    'if';

// $ANTLR src "ul.g" 217
ELSE             :    'else';

// $ANTLR src "ul.g" 219
WHILE            :    'while';

// $ANTLR src "ul.g" 221
PRINT            :    'print';

// $ANTLR src "ul.g" 223
PRINTLN          :    'println';

// $ANTLR src "ul.g" 225
RETURN           :    'return';

// $ANTLR src "ul.g" 227
TRUE             :    'true';

// $ANTLR src "ul.g" 229
FALSE            :    'false';

// $ANTLR src "ul.g" 231
SEMICOLON        :    ';';

// $ANTLR src "ul.g" 233
OPENPARENTHESIS  :    '(';

// $ANTLR src "ul.g" 235
CLOSEPARENTHESIS :    ')';

// $ANTLR src "ul.g" 237
OPENBRACKET      :    '[';

// $ANTLR src "ul.g" 239
CLOSEBRACKET     :    ']';

// $ANTLR src "ul.g" 241
OPENBRACE        :    '{';

// $ANTLR src "ul.g" 243
CLOSEBRACE       :    '}';

// $ANTLR src "ul.g" 245
COMMA            :    ',';

// $ANTLR src "ul.g" 247
SINGLEEQUALS     :    '=';

// $ANTLR src "ul.g" 249
STAR             :    '*';

// $ANTLR src "ul.g" 251
PLUS             :    '+';

// $ANTLR src "ul.g" 253
MINUS            :    '-';

// $ANTLR src "ul.g" 255
LESSTHAN         :    '<';

// $ANTLR src "ul.g" 257
DOUBLEEQUALS     :    '==';

// $ANTLR src "ul.g" 259
INTEGERCONSTANT
    :    ('0'..'9')+
    ;

// $ANTLR src "ul.g" 263
FLOATCONSTANT
    :    ('0'..'9')+ '.' ('0'..'9')+
    ;

// $ANTLR src "ul.g" 267
CHARACTERCONSTANT
    :    '\u0027' ( 'a'..'z' | 'A'..'Z' | ' ' | '0'..'9') '\u0027'
    ;

// $ANTLR src "ul.g" 271
STRINGCONSTANT
    :    '\u0022' ( 'a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '\u0022'
    ;

// $ANTLR src "ul.g" 275
ID
    :    (( 'a'..'z' | 'A'..'Z') | '_') (( 'a'..'z' | 'A'..'Z') | '_' | '0'..'9')*
    ;

// $ANTLR src "ul.g" 279
WHITESPACE
    :    ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;}
    ;

// $ANTLR src "ul.g" 283
COMMENT
    :    '//' ~('\r' | '\n')* {$channel = HIDDEN;}
    ;
