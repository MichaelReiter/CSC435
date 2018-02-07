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
    :    es = emptyStatement{ s = es; }
    |    exprs = expressionStatement { s = exprs; }
    |    as = assignmentStatement { s = as; }
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

emptyStatement returns [EmptyStatement es]
    :    SEMICOLON
    { es = new EmptyStatement(); }
    ;

expressionStatement returns [ExpressionStatement exprs]
    :    e = expression SEMICOLON
    { exprs = new ExpressionStatement(e); }
    ;

assignmentStatement returns [AssignmentStatement as]
    :    id = identifier SINGLEEQUALS e = expression SEMICOLON
    { as = new AssignmentStatement(id, e); }
    ;

arrayAssignmentStatement returns [ArrayAssignmentStatement aas]
    :    a = arrayReference SINGLEEQUALS e = expression SEMICOLON
    { aas = new ArrayAssignmentStatement(a, e); }
    ;

whileStatement returns [WhileStatement w]
    :    WHILE OPENPARENTHESIS e = expression CLOSEPARENTHESIS b = block
    { w = new WhileStatement(e, b); }
    ;

returnStatement returns [ReturnStatement r]
    :    RETURN (e = expression)? SEMICOLON
    { r = new ReturnStatement(e); }
    ;

printStatement returns [PrintStatement p]
    :    PRINT e = expression SEMICOLON
    { p = new PrintStatement(e); }
    ;

printlnStatement returns [PrintlnStatement pln]
    :    PRINTLN e = expression SEMICOLON
    { pln = new PrintlnStatement(e); }
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

atom returns [Expression e]
    :    ide = identifierExpression { e = ide; }
    |    l = literal { e = l; }
    |    pe = parenthesisExpression { e = pe; }
    |    fc = functionCall { e = fc; }
    |    are = arrayReferenceExpression { e = are; }
    ;

identifierExpression returns [Expression e]
    :    id = identifier
    { e = new IdentifierExpression(id); }
    ;

parenthesisExpression returns [ParenthesisExpression pe]
    :    OPENPARENTHESIS e = expression CLOSEPARENTHESIS
    { pe = new ParenthesisExpression(e); }
    ;

functionCall returns [FunctionCall fc]
    :    id = identifier OPENPARENTHESIS el = expressionList CLOSEPARENTHESIS
    { fc = new FunctionCall(id, el); }
    ;

arrayReferenceExpression returns [ArrayReferenceExpression are]
    :    a = arrayReference
    { are = new ArrayReferenceExpression(a); }
    ;

arrayReference returns [ArrayReference ar]
    :    id = identifier OPENBRACKET e = expression CLOSEBRACKET
    { ar = new ArrayReference(id, e); }
    ;

multiplyExpression returns [Expression e]
@init {
    Expression it = null;
}
@after {
    e = it;
}
    :    e1 = atom { it = e1; } (STAR e2 = atom { it = new MultiplyExpression(it, e2); })*
    ;

// This is a major hack. I can't seem to find a better way at the moment.
addExpression returns [Expression e]
@init {
    Expression it = null;
}
@after {
    e = it;
}
    :    e1 = multiplyExpression { it = e1; } (op = (PLUS|MINUS) e2 = multiplyExpression
    {
        if (op != null) {
            if (op.getText().equals("+")) {
                it = new AddExpression(it, e2);
            } else if (op.getText().equals("-")) {
                it = new SubtractExpression(it, e2);
            } else {
                System.out.println("Something went wrong");
                it = new AddExpression(it, e2);                
            }
        } else {
            // Default to plus
            it = new AddExpression(it, e2);
        }
    })*
    ;

lessThanExpression returns [Expression e]
@init {
    Expression it = null;
}
@after {
    e = it;
}
    :    e1 = addExpression { it = e1; } (LESSTHAN e2 = addExpression { it = new LessThanExpression(it, e2); })*
    ;

expression returns [Expression e]
@init {
    Expression it = null;
}
@after {
    e = it;
}
    :    e1 = lessThanExpression { it = e1; } (DOUBLEEQUALS e2 = lessThanExpression { it = new EqualityExpression(it, e2); })*
    ;

expressionList returns [ExpressionList el]
@init
{
    el = new ExpressionList();
}
    :    e1 = expression { el.addElement(e1); } (COMMA e2 = expression { el.addElement(e2); })*
    |
    ;

identifier returns [Identifier id]
    :    ID
    { id = new Identifier($ID.text); }
    ;

literal returns [Literal l]
    :    il = integerLiteral { l = il; }
    |    fl = floatLiteral { l = fl; }
    |    cl = characterLiteral { l = cl; }
    |    sl = stringLiteral { l = sl; }
    |    bl = booleanLiteral { l = bl; }
    ;

integerLiteral returns [IntegerLiteral il]
    :    INTEGERCONSTANT
    {
        int value = Integer.parseInt($INTEGERCONSTANT.text);
        il = new IntegerLiteral(value);
    }
    ;

floatLiteral returns [FloatLiteral fl]
    :    FLOATCONSTANT
    {
        float value = Float.parseFloat($FLOATCONSTANT.text);
        fl = new FloatLiteral(value);
    }
    ;

characterLiteral returns [CharacterLiteral cl]
    :    CHARACTERCONSTANT
    {
        char value = ($CHARACTERCONSTANT.text).charAt(1);
        cl = new CharacterLiteral(value);
    }
    ;

stringLiteral returns [StringLiteral sl]
    :    STRINGCONSTANT
    {
        String value = $STRINGCONSTANT.text;
        sl = new StringLiteral(value);
    }
    ;

booleanLiteral returns [BooleanLiteral bl]
    :    TRUE
    { bl = new BooleanLiteral(true); }
    |    FALSE
    { bl = new BooleanLiteral(false); }    
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

intt
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