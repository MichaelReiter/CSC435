package ast;

import type.*;

public class PrintVisitor implements Visitor {
    public PrintVisitor() {}

    public void visit(AddExpression e) {
        e.e1.accept(this);
		System.out.print(" + ");
		e.e2.accept(this);
    }

    public void visit(ArrayType a) {
        // System.out.print();
    }

    public void visit(ArrayAssignment s) {
        // System.out.print();
    }

    public void visit(ArrayReference a) {
        // System.out.print();
    }

    public void visit(Block b) {
        // System.out.print();
    }

    public void visit(BooleanLiteral b) {
        // System.out.print(b.value);
    }

    public void visit(CharacterLiteral c) {
        // System.out.print(c.value);
    }

    // public void visit(DoStatement s) {
        // System.out.print();
    }

    public void visit(EqualityExpression e) {
        // System.out.print();
    }

    public void visit(ExpressionStatement e) {
        // System.out.print();
    }

    public void visit(FloatLiteral f) {
        // System.out.print(f.value);
    }

    public void visit(FormalParameter p) {
        // System.out.print();
    }

    public void visit(Function f) {
        // System.out.print();
    }

    public void visit(FunctionBody f) {
        // System.out.print();
    }

    public void visit(FunctionCall f) {
        // System.out.print();
    }

    public void visit(FunctionDeclaration f) {
        // System.out.print();
    }

    public void visit(Identifier i) {
        // System.out.print(i.name);
    }

    public void visit(IdentifierValue v) {
        // System.out.print();
    }

    public void visit(IfStatement i) {
        // System.out.print();
    }

    public void visit(IfElseStatement i) {
        // System.out.print();
    }

    public void visit(IntegerLiteral i) {
        // System.out.print(i.value);
    }

    public void visit(LessThanExpression e) {
        // System.out.print();
    }

    public void visit(MultiplyExpression e) {
        // System.out.print();
    }

    public void visit(ParenthesisExpression p) {
        // System.out.print();
    }

    public void visit(PrintlnStatement s) {
        // System.out.print();
    }

    public void visit(PrintStatement s) {
        // System.out.print();
    }

    public void visit(Program p) {
        for (Function f : p.functionList) {
            f.accept(this);
        }
    }

    public void visit(ReturnStatement s) {
        // System.out.print();
    }

    public void visit(StringLiteral s) {
        // System.out.print(s.value);
    }

    public void visit(SubtractExpression e) {
        // System.out.print();
    }

    public void visit(Type t) {
        // System.out.print();        
    }

    public void visit(TypeNode t) {
        // System.out.print();
    }

    public void visit(VariableAssignment s) {
        // System.out.print();
    }

    public void visit(VariableDeclaration v) {
        // System.out.print();
    }

    public void visit(WhileStatement s) {
        // System.out.print();
    }
}