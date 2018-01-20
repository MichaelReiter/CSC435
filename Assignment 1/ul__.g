lexer grammar ul;

// $ANTLR src "ul.g" 194
TYPE
    :    'int'
    |    'float'
    |    'char'
    |    'string'
    |    'boolean'
    |    'void'
    ;

// $ANTLR src "ul.g" 203
IF               :    'if';

// $ANTLR src "ul.g" 205
ELSE             :    'else';

// $ANTLR src "ul.g" 207
WHILE            :    'while';

// $ANTLR src "ul.g" 209
PRINT            :    'print';

// $ANTLR src "ul.g" 211
PRINTLN          :    'println';

// $ANTLR src "ul.g" 213
RETURN           :    'return';

// $ANTLR src "ul.g" 215
TRUE             :    'true';

// $ANTLR src "ul.g" 217
FALSE            :    'false';

// $ANTLR src "ul.g" 219
SEMICOLON        :    ';';

// $ANTLR src "ul.g" 221
OPENPARENTHESIS  :    '(';

// $ANTLR src "ul.g" 223
CLOSEPARENTHESIS :    ')';

// $ANTLR src "ul.g" 225
OPENBRACKET      :    '[';

// $ANTLR src "ul.g" 227
CLOSEBRACKET     :    ']';

// $ANTLR src "ul.g" 229
OPENBRACE        :    '{';

// $ANTLR src "ul.g" 231
CLOSEBRACE       :    '}';

// $ANTLR src "ul.g" 233
COMMA            :    ',';

// $ANTLR src "ul.g" 235
SINGLEEQUALS     :    '=';

// $ANTLR src "ul.g" 237
STAR             :    '*';

// $ANTLR src "ul.g" 239
PLUS             :    '+';

// $ANTLR src "ul.g" 241
MINUS            :    '-';

// $ANTLR src "ul.g" 243
LESSTHAN         :    '<';

// $ANTLR src "ul.g" 245
DOUBLEEQUALS     :    '==';

// $ANTLR src "ul.g" 247
INTEGERCONSTANT
    :    ('0'..'9')+
    ;

// $ANTLR src "ul.g" 251
FLOATCONSTANT
    :    ('0'..'9')+ '.' ('0'..'9')+
    ;

// $ANTLR src "ul.g" 255
CHARACTERCONSTANT
    :    '\u0027' ( 'a'..'z' | 'A'..'Z' | ' ' | '0'..'9') '\u0027'
    ;

// $ANTLR src "ul.g" 259
STRINGCONSTANT
    :    '\u0022' ( 'a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '\u0022'
    ;

// $ANTLR src "ul.g" 263
ID
    :    (( 'a'..'z' | 'A'..'Z') | '_') (( 'a'..'z' | 'A'..'Z') | '_' | '0'..'9')*
    ;

// $ANTLR src "ul.g" 267
WHITESPACE
    :    ( '\t' | ' ' | ('\r' | '\n') )+ {$channel = HIDDEN;}
    ;

// $ANTLR src "ul.g" 271
COMMENT
    :    '//' ~('\r' | '\n')* {$channel = HIDDEN;}
    ;
