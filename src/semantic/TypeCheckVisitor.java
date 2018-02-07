package semantic;

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
import environment.Environment;
import type.ArrayType;
import type.Type;

public class TypeCheckVisitor implements TypeVisitor {
    private Environment<String, FunctionDeclaration> functionEnvironment;
    private Environment<String, Type> variableEnvironment;

    private String currentFunction;
    private Type currentFunctionReturnType;

    public TypeCheckVisitor() {}

    public Type visit(AddExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("+");
            e.getRightExpression().accept(this);
        }
    }

    public Type visit(ArrayAssignmentStatement s) {
        this.printIndentation();
        s.getArrayReference().accept(this);
        System.out.print("=");
        s.getExpression().accept(this);
        System.out.println(";");
    }

    public Type visit(ArrayReference a) {
        a.getIdentifier().accept(this);
        System.out.print("[");
        a.getExpression().accept(this);
        System.out.print("]");
    }

    public Type visit(ArrayReferenceExpression a) {
        a.getArrayReference().accept(this);
    }

    public Type visit(ArrayType a) {
        System.out.print(a + " ");
    }

    public Type visit(AssignmentStatement a) {
        this.printIndentation();
        a.getIdentifier().accept(this);
        System.out.print("=");
        a.getExpression().accept(this);
        System.out.println(";");
    }

    public Type visit(Block b) {
        this.printIndentation();
        System.out.println("{");
        b.getStatementList().accept(this);
        this.printIndentation();
        System.out.println("}");
    }

    public Type visit(BooleanLiteral b) {
        System.out.print(b.getValue());
    }

    public Type visit(CharacterLiteral c) {
        System.out.print("\'");
        System.out.print(c.getValue());
        System.out.print("\'");
    }

    public Type visit(Declaration d) {
        d.getType().accept(this);
        d.getIdentifier().accept(this);
    }

    public Type visit(EmptyStatement e) {
        this.printIndentation();
        System.out.println(";");
    }

    public Type visit(EqualityExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("==");
            e.getRightExpression().accept(this);
        }
    }

    public Type visit(ExpressionList e) {
        for (int i = 0; i < e.size(); i++) {
            Expression expr = e.elementAt(i);
            expr.accept(this);
            if (i != e.size() - 1) {
                System.out.print(",");
            }
        }
    }

    public Type visit(ExpressionStatement e) {
        this.printIndentation();
        e.getExpression().accept(this);
        System.out.println(";");
    }

    public Type visit(FloatLiteral f) {
        System.out.print(f.getValue());
    }

    public Type visit(FormalParameters p) {
        for (int i = 0; i < p.size(); i++) {
            Declaration d = p.elementAt(i);
            d.accept(this);
            if (i != p.size() - 1) {
                System.out.print(", ");
            }
        }
    }

    public Type visit(Function f) {
        f.getFunctionDeclaration().accept(this);
        f.getFunctionBody().accept(this);
        System.out.println();
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
    }

    public Type visit(FunctionCall f) {
        f.getIdentifier().accept(this);
        System.out.print("(");
        f.getExpressionList().accept(this);
        System.out.print(")");
    }

    public Type visit(FunctionDeclaration f) {
        f.getDeclaration().accept(this);
        System.out.print(" (");
        f.getFormalParameters().accept(this);
        System.out.println(")");
    }

    public Type visit(Identifier i) {
        System.out.print(i.getName());
    }

    public Type visit(IdentifierExpression i) {
        i.getIdentifier().accept(this);
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
    }

    public Type visit(IfStatement i) {
        this.printIndentation();
        System.out.print("if (");
        i.getExpression().accept(this);
        System.out.println(")");
        i.getBlock().accept(this);
    }

    public Type visit(IntegerLiteral i) {
        System.out.print(i.getValue());
    }

    public Type visit(LessThanExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("<");
            e.getRightExpression().accept(this);
        }
    }

    public Type visit(MultiplyExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("*");
            e.getRightExpression().accept(this);
        }
    }

    public Type visit(ParenthesisExpression p) {
        System.out.print("(");
        p.getExpression().accept(this);
        System.out.print(")");
    }

    public Type visit(PrintlnStatement s) {
        this.printIndentation();
        System.out.print("println ");
        s.getExpression().accept(this);
        System.out.println(";");
    }

    public Type visit(PrintStatement s) {
        this.printIndentation();
        System.out.print("print ");
        s.getExpression().accept(this);
        System.out.println(";");
    }

    public Type visit(Program p) {
        for (Function f : p.getFunctions()) {
            f.accept(this);
        }
    }

    public Type visit(ReturnStatement s) {
        this.printIndentation();
        System.out.print("return");
        if (s.getExpression() != null) {
            System.out.print(" ");
            s.getExpression().accept(this);
        }
        System.out.println(";");
    }

    public Type visit(StatementList sl) {
        for (ast.Statement s : sl.getStatements()) {
            s.accept(this);
        }
    }

    public Type visit(StringLiteral s) {
        System.out.print(s.getValue());
    }

    public Type visit(SubtractExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            System.out.print("-");
            e.getRightExpression().accept(this);
        }
    }

    public Type visit(Type t) {
        System.out.print(t + " ");
    }

    public Type visit(TypeNode t) {
        t.getType().accept(this);
    }

    public Type visit(VariableDeclaration v) {
        this.printIndentation();
        v.getDeclaration().accept(this);
        System.out.println(";");
    }

    public Type visit(WhileStatement s) {
        this.printIndentation();
        System.out.print("while (");
        s.getExpression().accept(this);
        System.out.println(")");
        s.getBlock().accept(this);
    }
}
