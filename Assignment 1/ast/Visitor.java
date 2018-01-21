package ast;

import type.*;

public interface Visitor {
    public void visit(AddExpression e);
    public void visit(ArrayAssignmentStatement s);
    public void visit(ArrayReference a);
    public void visit(ArrayType a);
    public void visit(AssignmentStatement a);
    public void visit(Atom a);
    public void visit(Block b);
    public void visit(BooleanLiteral b);
    public void visit(CharacterLiteral c);
    public void visit(Declaration d);
    public void visit(EmptyStatement e);
    public void visit(EqualityExpression e);
    public void visit(ExpressionStatement e);
    public void visit(FloatLiteral f);
    public void visit(FormalParameters p);
    public void visit(Function f);
    public void visit(FunctionBody f);
    public void visit(FunctionCall f);
    public void visit(FunctionDeclaration f);
    public void visit(Identifier i);
    public void visit(IdentifierExpression i);
    public void visit(IfElseStatement i);
    public void visit(IfStatement i);
    public void visit(IntegerLiteral i);
    public void visit(LessThanExpression e);
    public void visit(MultiplyExpression e);
    public void visit(ParenthesisExpression p);
    public void visit(PrintlnStatement s);
    public void visit(PrintStatement s);
    public void visit(Program p);
    public void visit(ReturnStatement s);
    public void visit(StatementList sl);
    public void visit(StringLiteral s);
    // public void visit(SubtractExpression e);
    public void visit(Type t);
    public void visit(TypeNode t);
    public void visit(VariableDeclaration v);
    public void visit(WhileStatement s);
}