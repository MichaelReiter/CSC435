grammar ul;

options {
    backtrack=true;
}

@header
{
    import ast.AddExpression;
    import ast.ArrayAssignmentStatement;
    import ast.ArrayReference;
    import ast.ArrayReferenceExpression;
    import ast.AssignmentStatement;
    import ast.Block;
    import ast.BooleanLiteral;
    import ast.CharacterLiteral;
    import ast.Declaration;
    import ast.EmptyStatement;
    import ast.EqualityExpression;
    import ast.Expression;
    import ast.ExpressionList;
    import ast.ExpressionStatement;
    import ast.FloatLiteral;
    import ast.FormalParameters;
    import ast.Function;
    import ast.FunctionBody;
    import ast.FunctionCall;
    import ast.FunctionDeclaration;
    import ast.Identifier;
    import ast.IdentifierExpression;
    import ast.IfElseStatement;
    import ast.IfStatement;
    import ast.IntegerLiteral;
    import ast.LessThanExpression;
    import ast.Literal;
    import ast.MultiplyExpression;
    import ast.ParenthesisExpression;
    import ast.PrintlnStatement;
    import ast.PrintStatement;
    import ast.Program;
    import ast.ReturnStatement;
    import ast.StatementList;
    import ast.StringLiteral;
    import ast.SubtractExpression;
    import ast.TypeNode;
    import ast.VariableDeclaration;
    import ast.WhileStatement;
    import type.ArrayType;
    import type.BooleanType;
    import type.CharType;
    import type.FloatType;
    import type.IntegerType;
    import type.StringType;
    import type.Type;
    import type.VoidType;
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
    : (f = function { p.add(f); })* EOF
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
        fd.setLine($OPENPARENTHESIS.getLine());
        fd.setOffset($OPENPARENTHESIS.getCharPositionInLine());
    }
    ;

formalParameters returns [FormalParameters args]
@init
{
    args = new FormalParameters();
}
    :    (d = declaration
    {
        args.add(d);
        args.setLine(d.getLine());
        args.setOffset(d.getOffset());
    }) (COMMA (d2 = declaration { args.add(d2); }))*
    |
    ;

declaration returns [Declaration d]
    :    ct = compoundType id = identifier
    {
        d = new Declaration(ct, id);
        d.setLine(id.getLine());
        d.setOffset(id.getOffset());
    }
    ;

functionBody returns [FunctionBody fb]
@init
{
    fb = new FunctionBody();
}
    :    OPENBRACE (vd = variableDeclaration { fb.addVariableDeclaration(vd); })* sl = statementList { fb.setStatementList(sl); } CLOSEBRACE
    ;

variableDeclaration returns [VariableDeclaration vd]
    :    d = declaration SEMICOLON
    {
        vd = new VariableDeclaration(d);
        vd.setLine(d.getType().getType().getLine());
        vd.setOffset(d.getType().getType().getOffset());
    }
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
    {
        as = new AssignmentStatement(id, e);
        as.setLine($SINGLEEQUALS.getLine());
        as.setOffset($SINGLEEQUALS.getCharPositionInLine() + 2);
    }
    ;

arrayAssignmentStatement returns [ArrayAssignmentStatement aas]
    :    a = arrayReference SINGLEEQUALS e = expression SEMICOLON
    {
        aas = new ArrayAssignmentStatement(a, e);
        aas.setLine($SINGLEEQUALS.getLine());
        aas.setOffset($SINGLEEQUALS.getCharPositionInLine() + 2);
    }
    ;

whileStatement returns [WhileStatement w]
    :    WHILE OPENPARENTHESIS e = expression CLOSEPARENTHESIS b = block
    {
        w = new WhileStatement(e, b);
        w.setLine($OPENPARENTHESIS.getLine());
        w.setOffset($OPENPARENTHESIS.getCharPositionInLine() + 1);
    }
    ;

returnStatement returns [ReturnStatement r]
    :    RETURN (e = expression)? SEMICOLON
    {
        r = new ReturnStatement(e);
        r.setLine($RETURN.getLine());
        r.setOffset($RETURN.getCharPositionInLine() + 1);
    }
    ;

printStatement returns [PrintStatement p]
    :    PRINT e = expression SEMICOLON
    {
        p = new PrintStatement(e);
        p.setLine($PRINT.getLine());
        p.setOffset($PRINT.getCharPositionInLine() + 6);
    }
    ;

printlnStatement returns [PrintlnStatement pln]
    :    PRINTLN e = expression SEMICOLON
    {
        pln = new PrintlnStatement(e);
        pln.setLine($PRINTLN.getLine());
        pln.setOffset($PRINTLN.getCharPositionInLine() + 6);
    }
    ;

ifElseStatement returns [IfElseStatement ies]
    :    IF OPENPARENTHESIS e = expression CLOSEPARENTHESIS b1 = block ELSE b2 = block
    {
        ies = new IfElseStatement(e, b1, b2);
        ies.setLine($OPENPARENTHESIS.getLine());
        ies.setOffset($OPENPARENTHESIS.getCharPositionInLine() + 1);
    }
    ;

ifStatement returns [IfStatement is]
    :    IF OPENPARENTHESIS e = expression CLOSEPARENTHESIS b = block
    {
        is = new IfStatement(e, b);
        is.setLine($OPENPARENTHESIS.getLine());
        is.setOffset($OPENPARENTHESIS.getCharPositionInLine() + 1);
    }
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
    {
        e = new IdentifierExpression(id);
        e.setLine(id.getLine());
        e.setOffset(id.getOffset());
    }
    ;

parenthesisExpression returns [ParenthesisExpression pe]
    :    OPENPARENTHESIS e = expression CLOSEPARENTHESIS
    { pe = new ParenthesisExpression(e); }
    ;

functionCall returns [FunctionCall fc]
    :    id = identifier OPENPARENTHESIS el = expressionList CLOSEPARENTHESIS
    {
        fc = new FunctionCall(id, el);
        fc.setLine($OPENPARENTHESIS.getLine());
        fc.setOffset($OPENPARENTHESIS.getCharPositionInLine() + 1);
    }
    ;

arrayReferenceExpression returns [ArrayReferenceExpression are]
    :    a = arrayReference
    { are = new ArrayReferenceExpression(a); }
    ;

arrayReference returns [ArrayReference ar]
    :    id = identifier OPENBRACKET e = expression CLOSEBRACKET
    {
        ar = new ArrayReference(id, e);
        ar.setLine($OPENBRACKET.getLine());
        ar.setOffset($OPENBRACKET.getCharPositionInLine() + 1);
    }
    ;

multiplyExpression returns [Expression e]
@init {
    Expression it = null;
}
@after {
    e = it;
}
    :    e1 = atom { it = e1; } (STAR e2 = atom
    {
        it = new MultiplyExpression(it, e2);
        it.setLine($STAR.getLine());
        it.setOffset($STAR.getCharPositionInLine() + 2);
    })*
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
                it.setLine(op.getLine());
                it.setOffset(op.getCharPositionInLine() + 2);
            } else if (op.getText().equals("-")) {
                it = new SubtractExpression(it, e2);
                it.setLine(op.getLine());
                it.setOffset(op.getCharPositionInLine() + 2);
            } else {
                System.out.println("Something went wrong");
                it = new AddExpression(it, e2);
                it.setLine(op.getLine());
                it.setOffset(op.getCharPositionInLine() + 2);
            }
        } else {
            // Default to plus
            it = new AddExpression(it, e2);
            it.setLine(op.getLine());
            it.setOffset(op.getCharPositionInLine() + 2);
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
    :    e1 = addExpression { it = e1; } (LESSTHAN e2 = addExpression
    {
        it = new LessThanExpression(it, e2);
        it.setLine($LESSTHAN.getLine());
        it.setOffset($LESSTHAN.getCharPositionInLine() + 2);
    })*
    ;

expression returns [Expression e]
@init {
    Expression it = null;
}
@after {
    e = it;
}
    :    e1 = lessThanExpression { it = e1; } (DOUBLEEQUALS e2 = lessThanExpression
    {
        it = new EqualityExpression(it, e2);
        it.setLine($DOUBLEEQUALS.getLine());
        it.setOffset($DOUBLEEQUALS.getCharPositionInLine() + 3);
    })*
    ;

expressionList returns [ExpressionList el]
@init
{
    el = new ExpressionList();
}
    :    e1 = expression { el.add(e1); } (COMMA e2 = expression { el.add(e2); })*
    |
    ;

identifier returns [Identifier id]
    :    ID
    {
        id = new Identifier($ID.text);
        id.setLine($ID.getLine());
        id.setOffset($ID.getCharPositionInLine());
    }
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
        il.setLine($INTEGERCONSTANT.getLine());
        il.setOffset($INTEGERCONSTANT.getCharPositionInLine());
    }
    ;

floatLiteral returns [FloatLiteral fl]
    :    FLOATCONSTANT
    {
        float value = Float.parseFloat($FLOATCONSTANT.text);
        fl = new FloatLiteral(value);
        fl.setLine($FLOATCONSTANT.getLine());
        fl.setOffset($FLOATCONSTANT.getCharPositionInLine());
    }
    ;

characterLiteral returns [CharacterLiteral cl]
    :    CHARACTERCONSTANT
    {
        char value = ($CHARACTERCONSTANT.text).charAt(1);
        cl = new CharacterLiteral(value);
        cl.setLine($CHARACTERCONSTANT.getLine());
        cl.setOffset($CHARACTERCONSTANT.getCharPositionInLine());
    }
    ;

stringLiteral returns [StringLiteral sl]
    :    STRINGCONSTANT
    {
        String value = $STRINGCONSTANT.text;
        sl = new StringLiteral(value);
        sl.setLine($STRINGCONSTANT.getLine());
        sl.setOffset($STRINGCONSTANT.getCharPositionInLine());
    }
    ;

booleanLiteral returns [BooleanLiteral bl]
    :    TRUE
    {
        bl = new BooleanLiteral(true);
        bl.setLine($TRUE.getLine());
        bl.setOffset($TRUE.getCharPositionInLine());
    }
    |    FALSE
    {
        bl = new BooleanLiteral(false);
        bl.setLine($FALSE.getLine());
        bl.setOffset($FALSE.getCharPositionInLine());
    }
    ;

type returns [Type t]
    :    INT
    {
        t = new IntegerType();
        t.setLine($INT.getLine());
        t.setOffset($INT.getCharPositionInLine());
    }
    |    FLOAT
    {
        t = new FloatType();
        t.setLine($FLOAT.getLine());
        t.setOffset($FLOAT.getCharPositionInLine());
    }
    |    CHAR
    {
        t = new CharType();
        t.setLine($CHAR.getLine());
        t.setOffset($CHAR.getCharPositionInLine());
    }
    |    STRING
    {
        t = new StringType();
        t.setLine($STRING.getLine());
        t.setOffset($STRING.getCharPositionInLine());
    }
    |    BOOLEAN
    {
        t = new BooleanType();
        t.setLine($BOOLEAN.getLine());
        t.setOffset($BOOLEAN.getCharPositionInLine());
    }
    |    VOID
    {
        t = new VoidType();
        t.setLine($VOID.getLine());
        t.setOffset($VOID.getCharPositionInLine());
    }
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
