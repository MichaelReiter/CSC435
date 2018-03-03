package ast;

import type.ArrayType;
import type.Type;

public interface Visitor<T> {
    public T visit(AddExpression e);
    public T visit(ArrayAssignmentStatement s);
    public T visit(ArrayReference a);
    public T visit(ArrayReferenceExpression a);
    public T visit(ArrayType a);
    public T visit(AssignmentStatement a);
    public T visit(Block b);
    public T visit(BooleanLiteral b);
    public T visit(CharacterLiteral c);
    public T visit(Declaration d);
    public T visit(EmptyStatement e);
    public T visit(EqualityExpression e);
    public T visit(ExpressionList e);
    public T visit(ExpressionStatement e);
    public T visit(FloatLiteral f);
    public T visit(FormalParameters p);
    public T visit(Function f);
    public T visit(FunctionBody f);
    public T visit(FunctionCall f);
    public T visit(FunctionDeclaration f);
    public T visit(Identifier i);
    public T visit(IdentifierExpression i);
    public T visit(IfElseStatement i);
    public T visit(IfStatement i);
    public T visit(IntegerLiteral i);
    public T visit(LessThanExpression e);
    public T visit(MultiplyExpression e);
    public T visit(ParenthesisExpression p);
    public T visit(PrintlnStatement s);
    public T visit(PrintStatement s);
    public T visit(Program p);
    public T visit(ReturnStatement s);
    public T visit(StatementList sl);
    public T visit(StringLiteral s);
    public T visit(SubtractExpression e);
    public T visit(Type t);
    public T visit(TypeNode t);
    public T visit(VariableDeclaration v);
    public T visit(WhileStatement s);
}
