package ast;

import java.lang.StringBuilder;

public class PrettyPrintVisitor implements Visitor<Void> {
    private int indentationDepth;

    public PrettyPrintVisitor() {
        this.indentationDepth = 1;
    }

    public Void visit(AddExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("+");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Void visit(ArrayAssignmentStatement s) {
        this.printIndentation();
        s.getArrayReference().accept(this);
        System.out.print("=");
        s.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Void visit(ArrayReference a) {
        a.getIdentifier().accept(this);
        System.out.print("[");
        a.getExpression().accept(this);
        System.out.print("]");
        return null;
    }

    public Void visit(ArrayReferenceExpression a) {
        a.getArrayReference().accept(this);
        return null;
    }

    public Void visit(AssignmentStatement a) {
        this.printIndentation();
        a.getIdentifier().accept(this);
        System.out.print("=");
        a.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Void visit(Block b) {
        this.printIndentation();
        System.out.println("{");
        this.indentationDepth += 1;
        b.getStatementList().accept(this);
        this.indentationDepth -= 1;
        this.printIndentation();
        System.out.println("}");
        return null;
    }

    public Void visit(BooleanLiteral b) {
        System.out.print(b.getValue());
        return null;
    }

    public Void visit(CharacterLiteral c) {
        System.out.print("\'");
        System.out.print(c.getValue());
        System.out.print("\'");
        return null;
    }

    public Void visit(Declaration d) {
        d.getType().accept(this);
        d.getIdentifier().accept(this);
        return null;
    }

    public Void visit(EmptyStatement e) {
        this.printIndentation();
        System.out.println(";");
        return null;
    }

    public Void visit(EqualityExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("==");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Void visit(ExpressionList e) {
        for (int i = 0; i < e.size(); i++) {
            Expression expr = e.get(i);
            expr.accept(this);
            if (i != e.size() - 1) {
                System.out.print(",");
            }
        }
        return null;
    }

    public Void visit(ExpressionStatement e) {
        this.printIndentation();
        e.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Void visit(FloatLiteral f) {
        System.out.print(f.getValue());
        return null;
    }

    public Void visit(FormalParameters p) {
        for (int i = 0; i < p.size(); i++) {
            Declaration d = p.get(i);
            d.accept(this);
            if (i != p.size() - 1) {
                System.out.print(", ");
            }
        }
        return null;
    }

    public Void visit(Function f) {
        f.getFunctionDeclaration().accept(this);
        f.getFunctionBody().accept(this);
        System.out.println();
        return null;
    }

    public Void visit(FunctionBody f) {
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

    public Void visit(FunctionCall f) {
        f.getIdentifier().accept(this);
        System.out.print("(");
        f.getExpressionList().accept(this);
        System.out.print(")");
        return null;
    }

    public Void visit(FunctionDeclaration f) {
        f.getDeclaration().accept(this);
        System.out.print(" (");
        f.getFormalParameters().accept(this);
        System.out.println(")");
        return null;
    }

    public Void visit(Identifier i) {
        System.out.print(i.getName());
        return null;
    }

    public Void visit(IdentifierExpression i) {
        i.getIdentifier().accept(this);
        return null;
    }

    public Void visit(IfElseStatement i) {
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

    public Void visit(IfStatement i) {
        this.printIndentation();
        System.out.print("if (");
        i.getExpression().accept(this);
        System.out.println(")");
        i.getBlock().accept(this);
        return null;
    }

    public Void visit(IntegerLiteral i) {
        System.out.print(i.getValue());
        return null;
    }

    public Void visit(LessThanExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("<");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Void visit(MultiplyExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("*");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Void visit(ParenthesisExpression p) {
        System.out.print("(");
        p.getExpression().accept(this);
        System.out.print(")");
        return null;
    }

    public Void visit(PrintlnStatement s) {
        this.printIndentation();
        System.out.print("println ");
        s.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Void visit(PrintStatement s) {
        this.printIndentation();
        System.out.print("print ");
        s.getExpression().accept(this);
        System.out.println(";");
        return null;
    }

    public Void visit(Program p) {
        for (Function f : p.getFunctions()) {
            f.accept(this);
        }
        return null;
    }

    public Void visit(ReturnStatement s) {
        this.printIndentation();
        System.out.print("return");
        if (s.getExpression() != null) {
            System.out.print(" ");
            s.getExpression().accept(this);
        }
        System.out.println(";");
        return null;
    }

    public Void visit(StatementList sl) {
        for (ast.Statement s : sl.getStatements()) {
            s.accept(this);
        }
        return null;
    }

    public Void visit(StringLiteral s) {
        System.out.print(s.getValue());
        return null;
    }

    public Void visit(SubtractExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("-");
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Void visit(TypeNode t) {
        System.out.print(t.getType() + " ");
        return null;
    }

    public Void visit(VariableDeclaration v) {
        this.printIndentation();
        v.getDeclaration().accept(this);
        System.out.println(";");
        return null;
    }

    public Void visit(WhileStatement s) {
        this.printIndentation();
        System.out.print("while (");
        s.getExpression().accept(this);
        System.out.println(")");
        s.getBlock().accept(this);
        return null;
    }

    private final void printIndentation() {
        int totalIndentation = 4 * this.indentationDepth;
        StringBuilder sb = new StringBuilder(totalIndentation);
        for (int i = 0; i < totalIndentation; i++) {
            sb.append(" ");
        }
        String spaces = sb.toString();
        System.out.print(spaces);
    }
}
