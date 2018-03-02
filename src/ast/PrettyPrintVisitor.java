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

    public Type visit(AddExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("+");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(ArrayAssignmentStatement s) {
        this.printIndentation();
        s.getArrayReference().accept(this);
        System.out.print("=");
        s.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Type visit(ArrayReference a) {
        a.getIdentifier().accept(this);
        System.out.print("[");
        a.getExpression().accept(this);
        System.out.print("]");
        return null;
    }

    public Type visit(ArrayReferenceExpression a) {
        a.getArrayReference().accept(this);
        return null;
    }

    public Type visit(ArrayType a) {
        System.out.print(a + " ");
        return null;
    }

    public Type visit(AssignmentStatement a) {
        this.printIndentation();
        a.getIdentifier().accept(this);
        System.out.print("=");
        a.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Type visit(Block b) {
        this.printIndentation();
        System.out.println("{");
        this.indentationDepth += 1;
        b.getStatementList().accept(this);
        this.indentationDepth -= 1;
        this.printIndentation();
        System.out.println("}");
        return null;
    }

    public Type visit(BooleanLiteral b) {
        System.out.print(b.getValue());
        return null;
    }

    public Type visit(CharacterLiteral c) {
        System.out.print("\'");
        System.out.print(c.getValue());
        System.out.print("\'");
        return null;
    }

    public Type visit(Declaration d) {
        d.getType().accept(this);
        d.getIdentifier().accept(this);
        return null;
    }

    public Type visit(EmptyStatement e) {
        this.printIndentation();
        System.out.println(";");
        return null;
    }

    public Type visit(EqualityExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("==");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(ExpressionList e) {
        for (int i = 0; i < e.size(); i++) {
            Expression expr = e.get(i);
            expr.accept(this);
            if (i != e.size() - 1) {
                System.out.print(",");
            }
        }
        return null;
    }

    public Type visit(ExpressionStatement e) {
        this.printIndentation();
        e.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Type visit(FloatLiteral f) {
        System.out.print(f.getValue());
        return null;
    }

    public Type visit(FormalParameters p) {
        for (int i = 0; i < p.size(); i++) {
            Declaration d = p.get(i);
            d.accept(this);
            if (i != p.size() - 1) {
                System.out.print(", ");
            }
        }
        return null;
    }

    public Type visit(Function f) {
        f.getFunctionDeclaration().accept(this);
        f.getFunctionBody().accept(this);
        System.out.println();
        return null;
    }

    public Type visit(FunctionBody f) {
        System.out.println("{");
        for (VariableDeclaration vd : f.getVariableDeclarations()) {
            vd.accept(this);
        }
        if (f.size() > 0) {
            System.out.println();
        }
        f.getStatementList().accept(this);
        System.out.println("}");
        return null;
    }

    public Type visit(FunctionCall f) {
        f.getIdentifier().accept(this);
        System.out.print("(");
        f.getExpressionList().accept(this);
        System.out.print(")");
        return null;
    }

    public Type visit(FunctionDeclaration f) {
        f.getDeclaration().accept(this);
        System.out.print(" (");
        f.getFormalParameters().accept(this);
        System.out.println(")");
        return null;
    }

    public Type visit(Identifier i) {
        System.out.print(i.getName());
        return null;
    }

    public Type visit(IdentifierExpression i) {
        i.getIdentifier().accept(this);
        return null;
    }

    public Type visit(IfElseStatement i) {
        this.printIndentation();
        System.out.print("if (");
        i.getExpression().accept(this);
        System.out.println(")");
        i.getIfBlock().accept(this);
        this.printIndentation();
        System.out.println("else");
        i.getElseBlock().accept(this);
        return null;
    }

    public Type visit(IfStatement i) {
        this.printIndentation();
        System.out.print("if (");
        i.getExpression().accept(this);
        System.out.println(")");
        i.getBlock().accept(this);
        return null;
    }

    public Type visit(IntegerLiteral i) {
        System.out.print(i.getValue());
        return null;
    }

    public Type visit(LessThanExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("<");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(MultiplyExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("*");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(ParenthesisExpression p) {
        System.out.print("(");
        p.getExpression().accept(this);
        System.out.print(")");
        return null;
    }

    public Type visit(PrintlnStatement s) {
        this.printIndentation();
        System.out.print("println ");
        s.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Type visit(PrintStatement s) {
        this.printIndentation();
        System.out.print("print ");
        s.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Type visit(Program p) {
        for (Function f : p.getFunctions()) {
            f.accept(this);
        }
        return null;
    }

    public Type visit(ReturnStatement s) {
        this.printIndentation();
        System.out.print("return");
        if (s.getExpression() != null) {
            System.out.print(" ");
            s.getExpression().accept(this);
        }
        System.out.println(";");
        return null;
    }

    public Type visit(StatementList sl) {
        for (ast.Statement s : sl.getStatements()) {
            s.accept(this);
        }
        return null;
    }

    public Type visit(StringLiteral s) {
        System.out.print(s.getValue());
        return null;
    }

    public Type visit(SubtractExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("-");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(Type t) {
        System.out.print(t + " ");
        return null;
    }

    public Type visit(TypeNode t) {
        t.getType().accept(this);
        return null;
    }

    public Type visit(VariableDeclaration v) {
        this.printIndentation();
        v.getDeclaration().accept(this);
        System.out.println(";");
        return null;
    }

    public Type visit(WhileStatement s) {
        this.printIndentation();
        System.out.print("while (");
        s.getExpression().accept(this);
        System.out.println(")");
        s.getBlock().accept(this);
        return null;
    }

    private final Type printIndentation() {
        int totalIndentation = 4 * this.indentationDepth;
        StringBuilder sb = new StringBuilder(totalIndentation);
        for (int i = 0; i < totalIndentation; i++) {
            sb.append(" ");
        }
        String spaces = sb.toString();
        System.out.print(spaces);
        return null;
    }
}
