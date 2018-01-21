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
    :    ct = compoundType id = identifier OPENPARENTHESIS args = formalParameters CLOSEPARENTHESIS
    {
        Declaration d = new Declaration(ct, id);
        fd = new FunctionDeclaration(d, args);
    }
    ;

formalParameters returns [FormalParameters args]
@init
{
    args = new FormalParameters();
}
    :    (d = declaration { args.addElement(d); }) (COMMA (d2 = declaration { args.addElement(d2); }))*
    |
    ;

declaration returns [Declaration d]
    :    ct = compoundType id = identifier
    { d = new Declaration(ct, id); }
    ;

functionBody returns [FunctionBody fb]
@init
{
    fb = new FunctionBody();
}
    :    OPENBRACE (vd = variableDeclaration { fb.addVariableDeclaration(vd); })* sl = statementList { fb.sl = sl; } CLOSEBRACE
    ;

variableDeclaration returns [VariableDeclaration vd]
    :    d = declaration SEMICOLON
    { vd = new VariableDeclaration(d); }
    ;

compoundType returns [TypeNode tn]
    :    t = type
    { tn = new TypeNode(t); }
    |    t = type OPENBRACKET INTEGERCONSTANT CLOSEBRACKET
    {
        int size = Integer.parseInt($INTEGERCONSTANT.text);
        ArrayType at = new ArrayType(t, size);
        tn = new TypeNode(at, size);
    }
    ;

statement returns [ast.Statement s]
    // :    SEMICOLON
    // |    expressionStatement
    :    as = assignmentStatement { s = as; }
    |    aas = arrayAssignmentStatement { s = aas; }
    |    w = whileStatement { s = w; }
    |    r = returnStatement { s = r; }
    |    p = printStatement { s = p; }
    |    pln = printlnStatement { s = pln; }
    |    ies = ifElseStatement { s = ies; }
    |    is = ifStatement { s = is; }
    ;

statementList returns [StatementList sl]
@init
{
    sl = new StatementList();
}
    :    (s = statement { sl.addStatement(s); })*
    ;

expressionStatement
    :    expression SEMICOLON
    ;

assignmentStatement returns [AssignmentStatement as]
    :    id = identifier SINGLEEQUALS e = expression SEMICOLON
    { as = new AssignmentStatement(id, e); }
    ;

arrayAssignmentStatement returns [ArrayAssignmentStatement aas]
    :    a = arrayReference SINGLEEQUALS e = expression SEMICOLON
    { aas = new ArrayAssignmentStatement(a, e); }
    ;

// TODO add expression
whileStatement returns [WhileStatement w]
    :    WHILE OPENPARENTHESIS e = expression CLOSEPARENTHESIS b = block
    { w = new WhileStatement(e, b); }
    ;

// TODO add expression
returnStatement returns [ReturnStatement r]
    :    RETURN (e = expression)? SEMICOLON
    { r = new ReturnStatement(e); }
    ;

// TODO add expression
printStatement returns [PrintStatement p]
    :    PRINT expression SEMICOLON
    { p = new PrintStatement(); }
    ;

// TODO add expression
printlnStatement returns [PrintlnStatement pln]
    :    PRINTLN expression SEMICOLON
    { pln = new PrintlnStatement(); }
    ;

ifElseStatement returns [IfElseStatement ies]
    :    IF OPENPARENTHESIS e = expression CLOSEPARENTHESIS b1 = block ELSE b2 =block
    { ies = new IfElseStatement(e, b1, b2); }
    ;

ifStatement returns [IfStatement is]
    :    IF OPENPARENTHESIS e = expression CLOSEPARENTHESIS b = block
    { is = new IfStatement(e, b); }
    ;

block returns [Block b]
    :    OPENBRACE sl = statementList CLOSEBRACE
    { b = new Block(sl); }
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

arrayReference returns [ArrayReference ar]
    :    id = identifier OPENBRACKET e = expression CLOSEBRACKET
    { ar = new ArrayReference(id, e); }
    ;

multiplyExpression
    :    atom (STAR atom)*
    ;

addExpression
    :    multiplyExpression ((PLUS|MINUS) multiplyExpression)*
    ;

lessThanExpression
    :    addExpression (LESSTHAN addExpression)*
    ;

equalityExpression
    :    lessThanExpression (DOUBLEEQUALS lessThanExpression)*
    ;

expression returns [Expression e]
    :    equalityExpression
    // { e = new EqualityExpression(); }
    ;

expressionList
    :    expression (COMMA expression)*
    |
    ;

identifier returns [Identifier id]
    :    ID
    { id = new Identifier($ID.text); }
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

// Lexer below

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
