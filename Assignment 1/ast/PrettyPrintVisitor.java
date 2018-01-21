package ast;

import java.lang.StringBuilder;

import jdk.nashorn.internal.ir.Statement;
import type.*;

public class PrettyPrintVisitor implements Visitor {
    int indentationDepth;

    public PrettyPrintVisitor() {
        this.indentationDepth = 1;
    }

    public void visit(AddExpression e) {
        // e.e1.accept(this);
		// System.out.print(" + ");
		// e.e2.accept(this);
    }

    public void visit(ArrayAssignmentStatement s) {
        this.printIndentation();
        s.a.accept(this);
        System.out.print(" = ");
        s.e.accept(this);
        System.out.println(";");
    }

    public void visit(ArrayReference a) {
        a.id.accept(this);
        System.out.print("[");
        a.e.accept(this);
        System.out.print("]");
    }

    public void visit(ArrayType a) {
        System.out.print(a + " ");
    }

    public void visit(AssignmentStatement a) {
        this.printIndentation();
        a.id.accept(this);
        System.out.print(" = ");
        a.e.accept(this);
        System.out.println(";");
    }
    
    public void visit(Atom a) {
        a.accept(this);
    }

    public void visit(Block b) {
        this.printIndentation();
        System.out.println("{");
        this.indentationDepth += 1;
        b.sl.accept(this);
        this.indentationDepth -= 1;
        this.printIndentation();
        System.out.println("}");
    }

    public void visit(BooleanLiteral b) {
        System.out.print(b.value);
    }

    public void visit(CharacterLiteral c) {
        System.out.print("\'");
        System.out.print(c.value);
        System.out.print("\'");
    }

    public void visit(Declaration d) {
        d.type.accept(this);
        d.id.accept(this);
    }

    public void visit(EmptyStatement e) {
        this.printIndentation();
        System.out.println(";");
    }

    public void visit(EqualityExpression e) {
        // System.out.print();
        e.accept(this);
    }

    public void visit(ExpressionStatement e) {
        this.printIndentation();
        e.e.accept(this);
        System.out.println(";");
    }

    public void visit(FloatLiteral f) {
        System.out.print(f.value);
    }

    public void visit(FormalParameters p) {
        for (int i = 0; i < p.parameters.size(); i++) {
            Declaration d = p.elementAt(i);
            d.accept(this);
            if (i != p.parameters.size() - 1) {
                System.out.print(", ");
            }
        }
    }

    public void visit(Function f) {
        f.fd.accept(this);
        f.fb.accept(this);
        System.out.println();
    }

    public void visit(FunctionBody f) {
        System.out.println("{");
        for (VariableDeclaration vd : f.variableDeclarations) {
            vd.accept(this);
        }
        if (f.variableDeclarations.size() > 0) {
            System.out.println();
        }
        f.sl.accept(this);
        System.out.println("}");
    }

    public void visit(FunctionCall f) {
        // System.out.print();
    }

    public void visit(FunctionDeclaration f) {
        f.d.type.accept(this);
        f.d.id.accept(this);
        System.out.print("(");
        f.args.accept(this);
        System.out.println(")");
    }

    public void visit(Identifier i) {
        System.out.print(i.name);
    }

    public void visit(IdentifierExpression i) {
        i.id.accept(this);
    }

    public void visit(IfElseStatement i) {
        this.printIndentation();
        System.out.print("if (");
        i.e.accept(this);
        System.out.println(")");
        i.b1.accept(this);
        this.printIndentation();
        System.out.println("else");
        i.b2.accept(this);
    }

    public void visit(IfStatement i) {
        this.printIndentation();
        System.out.print("if (");
        i.e.accept(this);
        System.out.println(")");
        i.b.accept(this);
    }

    public void visit(IntegerLiteral i) {
        System.out.print(i.value);
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
        this.printIndentation();
        System.out.print("println ");
        s.e.accept(this);
        System.out.println(";");
    }

    public void visit(PrintStatement s) {
        this.printIndentation();
        System.out.print("print ");
        s.e.accept(this);
        System.out.println(";");
    }

    public void visit(Program p) {
        for (Function f : p.functionList) {
            f.accept(this);
        }
    }

    public void visit(ReturnStatement s) {
        this.printIndentation();
        System.out.print("return");
        if (s.e != null) {
            System.out.print(" ");
            s.e.accept(this);
        }
        System.out.println(";");
    }

    public void visit(StatementList sl) {
        for (ast.Statement s : sl.l) {
            s.accept(this);
        }
    }

    public void visit(StringLiteral s) {
        System.out.print(s.value);
    }

    // public void visit(SubtractExpression e) {
    //     // System.out.print();
    // }

    public void visit(Type t) {
        System.out.print(t + " ");
    }

    public void visit(TypeNode t) {
        t.t.accept(this);
    }

    public void visit(VariableDeclaration v) {
        this.printIndentation();
        v.d.accept(this);
        System.out.println(";");
    }

    public void visit(WhileStatement s) {
        this.printIndentation();
        System.out.print("while (");
        s.e.accept(this);
        System.out.println(")");
        s.b.accept(this);
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
