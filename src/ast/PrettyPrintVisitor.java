package ast;

import java.lang.StringBuilder;

import type.ArrayType;
import type.Type;

public class PrettyPrintVisitor implements Visitor {
    // I'm taking the cowards way out and using global state here
    private int indentationDepth;

    public PrettyPrintVisitor() {
        this.indentationDepth = 1;
    }

    public void visit(AddExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("+");
            e.getRightExpression().accept(this);
        }
    }

    public void visit(ArrayAssignmentStatement s) {
        this.printIndentation();
        s.getArrayReference().accept(this);
        System.out.print("=");
        s.getExpression().accept(this);
        System.out.println(";");
    }

    public void visit(ArrayReference a) {
        a.getIdentifier().accept(this);
        System.out.print("[");
        a.getExpression().accept(this);
        System.out.print("]");
    }

    public void visit(ArrayReferenceExpression a) {
        a.getArrayReference().accept(this);
    }

    public void visit(ArrayType a) {
        System.out.print(a + " ");
    }

    public void visit(AssignmentStatement a) {
        this.printIndentation();
        a.getIdentifier().accept(this);
        System.out.print("=");
        a.getExpression().accept(this);
        System.out.println(";");
    }

    public void visit(Block b) {
        this.printIndentation();
        System.out.println("{");
        this.indentationDepth += 1;
        b.getStatementList().accept(this);
        this.indentationDepth -= 1;
        this.printIndentation();
        System.out.println("}");
    }

    public void visit(BooleanLiteral b) {
        System.out.print(b.getValue());
    }

    public void visit(CharacterLiteral c) {
        System.out.print("\'");
        System.out.print(c.getValue());
        System.out.print("\'");
    }

    public void visit(Declaration d) {
        d.getType().accept(this);
        d.getIdentifier().accept(this);
    }

    public void visit(EmptyStatement e) {
        this.printIndentation();
        System.out.println(";");
    }

    public void visit(EqualityExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("==");
            e.getRightExpression().accept(this);
        }
    }

    public void visit(ExpressionList e) {
        for (int i = 0; i < e.size(); i++) {
            Expression expr = e.elementAt(i);
            expr.accept(this);
            if (i != e.size() - 1) {
                System.out.print(",");
            }
        }
    }

    public void visit(ExpressionStatement e) {
        this.printIndentation();
        e.getExpression().accept(this);
        System.out.println(";");
    }

    public void visit(FloatLiteral f) {
        System.out.print(f.getValue());
    }

    public void visit(FormalParameters p) {
        for (int i = 0; i < p.size(); i++) {
            Declaration d = p.elementAt(i);
            d.accept(this);
            if (i != p.size() - 1) {
                System.out.print(", ");
            }
        }
    }

    public void visit(Function f) {
        f.getFunctionDeclaration().accept(this);
        f.getFunctionBody().accept(this);
        System.out.println();
    }

    public void visit(FunctionBody f) {
        System.out.println("{");
        for (VariableDeclaration vd : f.getVariableDeclarations()) {
            vd.accept(this);
        }
        if (f.size() > 0) {
            System.out.println();
        }
        f.getStatementList().accept(this);
        System.out.println("}");
    }

    public void visit(FunctionCall f) {
        f.getIdentifier().accept(this);
        System.out.print("(");
        f.getExpressionList().accept(this);
        System.out.print(")");
    }

    public void visit(FunctionDeclaration f) {
        f.getDeclaration().accept(this);
        System.out.print(" (");
        f.getFormalParameters().accept(this);
        System.out.println(")");
    }

    public void visit(Identifier i) {
        System.out.print(i.getName());
    }

    public void visit(IdentifierExpression i) {
        i.getIdentifier().accept(this);
    }

    public void visit(IfElseStatement i) {
        this.printIndentation();
        System.out.print("if (");
        i.getExpression().accept(this);
        System.out.println(")");
        i.getIfBlock().accept(this);
        this.printIndentation();
        System.out.println("else");
        i.getElseBlock().accept(this);
    }

    public void visit(IfStatement i) {
        this.printIndentation();
        System.out.print("if (");
        i.getExpression().accept(this);
        System.out.println(")");
        i.getBlock().accept(this);
    }

    public void visit(IntegerLiteral i) {
        System.out.print(i.getValue());
    }

    public void visit(LessThanExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("<");
            e.getRightExpression().accept(this);
        }
    }

    public void visit(MultiplyExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("*");
            e.getRightExpression().accept(this);
        }
    }

    public void visit(ParenthesisExpression p) {
        System.out.print("(");
        p.getExpression().accept(this);
        System.out.print(")");
    }

    public void visit(PrintlnStatement s) {
        this.printIndentation();
        System.out.print("println ");
        s.getExpression().accept(this);
        System.out.println(";");
    }

    public void visit(PrintStatement s) {
        this.printIndentation();
        System.out.print("print ");
        s.getExpression().accept(this);
        System.out.println(";");
    }

    public void visit(Program p) {
        for (Function f : p.getFunctions()) {
            f.accept(this);
        }
    }

    public void visit(ReturnStatement s) {
        this.printIndentation();
        System.out.print("return");
        if (s.getExpression() != null) {
            System.out.print(" ");
            s.getExpression().accept(this);
        }
        System.out.println(";");
    }

    public void visit(StatementList sl) {
        for (ast.Statement s : sl.getStatements()) {
            s.accept(this);
        }
    }

    public void visit(StringLiteral s) {
        System.out.print(s.getValue());
    }

    public void visit(SubtractExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("-");
            e.getRightExpression().accept(this);
        }
    }

    public void visit(Type t) {
        System.out.print(t + " ");
    }

    public void visit(TypeNode t) {
        t.getType().accept(this);
    }

    public void visit(VariableDeclaration v) {
        this.printIndentation();
        v.getDeclaration().accept(this);
        System.out.println(";");
    }

    public void visit(WhileStatement s) {
        this.printIndentation();
        System.out.print("while (");
        s.getExpression().accept(this);
        System.out.println(")");
        s.getBlock().accept(this);
    }

    private void printIndentation() {
        int totalIndentation = 4 * this.indentationDepth;
        StringBuilder sb = new StringBuilder(totalIndentation);
        for (int i = 0; i < totalIndentation; i++){
            sb.append(" ");
        }
        String spaces = sb.toString();
        System.out.print(spaces);
    }
}
