package ir;

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
import ast.Visitor;
import ast.WhileStatement;
import type.ArrayType;
// import type.BooleanType;
// import type.CharType;
// import type.FloatType;
// import type.IntegerType;
// import type.StringType;
// import type.Type;
// import type.VoidType;

public class IRVisitor implements TempVisitor {
    public IRVisitor() {}

    public Temp visit(AddExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;

        // In class example below
        Temp lhs = e.getLeftExpression().accept(this);
        Temp rhs = e.getRightExpression().accept(this);
        Temp dest = temp.getTemp(lhs.getType()); // factory function
        // Corless is using an enum. Note to self: replace with separate classes for each operation
        // i.e. AddOperation
        Instruction instruction = new BinaryOperation(dest, lhs, rhs, BinaryOperation.Add);

        // list of instructions for the current function
        instructions.add(instruction);
        temps.returnTemp(lhs);
        temps.returnTemp(rhs);
    }

    public Temp visit(ArrayAssignmentStatement s) {
        s.getArrayReference().accept(this);
        //
        s.getExpression().accept(this);
        //
        return null;
    }

    public Temp visit(ArrayReference a) {
        a.getIdentifier().accept(this);
        //
        a.getExpression().accept(this);
        //
        return null;
    }

    public Temp visit(ArrayReferenceExpression a) {
        a.getArrayReference().accept(this);
        return null;
    }

    public Temp visit(ArrayType a) {
        //
        return null;
    }

    public Temp visit(AssignmentStatement a) {
        a.getIdentifier().accept(this);
        //
        a.getExpression().accept(this);
        //
        return null;
    }

    public Temp visit(Block b) {
        //
        b.getStatementList().accept(this);
        //
        return null;
    }

    public Temp visit(BooleanLiteral b) {
        //
        return null;
    }

    public Temp visit(CharacterLiteral c) {
        //
        //
        //
        return null;
    }

    public Temp visit(Declaration d) {
        d.getType().accept(this);
        d.getIdentifier().accept(this);
        return null;
    }

    public Temp visit(EmptyStatement e) {
        //
        return null;
    }

    public Temp visit(EqualityExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Temp visit(ExpressionList e) {
        for (int i = 0; i < e.size(); i++) {
            Expression expr = e.get(i);
            expr.accept(this);
            if (i != e.size() - 1) {
                //
            }
        }
        return null;
    }

    public Temp visit(ExpressionStatement e) {
        e.getExpression().accept(this);
        //
        return null;
    }

    public Temp visit(FloatLiteral f) {
        //
        return null;
    }

    public Temp visit(FormalParameters p) {
        for (int i = 0; i < p.size(); i++) {
            Declaration d = p.get(i);
            d.accept(this);
            if (i != p.size() - 1) {
                //
            }
        }
        return null;
    }

    public Temp visit(Function f) {
        f.getFunctionDeclaration().accept(this);
        f.getFunctionBody().accept(this);
        //
        return null;
    }

    public Temp visit(FunctionBody f) {
        //
        for (VariableDeclaration vd : f.getVariableDeclarations()) {
            vd.accept(this);
        }
        if (f.size() > 0) {
            //
        }
        f.getStatementList().accept(this);
        //
        return null;
    }

    public Temp visit(FunctionCall f) {
        f.getIdentifier().accept(this);
        //
        f.getExpressionList().accept(this);
        //
        return null;
    }

    public Temp visit(FunctionDeclaration f) {
        f.getDeclaration().accept(this);
        //
        f.getFormalParameters().accept(this);
        //
        return null;
    }

    public Temp visit(Identifier i) {
        //
        return null;
    }

    public Temp visit(IdentifierExpression i) {
        i.getIdentifier().accept(this);
        return null;
    }

    public Temp visit(IfElseStatement i) {
        //
        i.getExpression().accept(this);
        //
        i.getIfBlock().accept(this);
        //
        i.getElseBlock().accept(this);
        return null;
    }

    public Temp visit(IfStatement i) {
        //
        i.getExpression().accept(this);
        //
        i.getBlock().accept(this);
        return null;

        // From Corless example in class
        Instruction instruction;
        Label l1 = new Label();
        Label l2 = new Label();
        Temp t = i.expr.accept(this);
        // need new temp here if i is LOCAL or PARAMETER so we don't mess up state
        in = new UnaryOperation(t, t);
    }

    public Temp visit(IntegerLiteral i) {
        //
        return null;
    }

    public Temp visit(LessThanExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Temp visit(MultiplyExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Temp visit(ParenthesisExpression p) {
        //
        p.getExpression().accept(this);
        //
        return null;
    }

    public Temp visit(PrintlnStatement s) {
        //
        s.getExpression().accept(this);
        //
        return null;
    }

    public Temp visit(PrintStatement s) {
        //
        s.getExpression().accept(this);
        //
        return null;
    }

    public Temp visit(Program p) {
        for (Function f : p.getFunctions()) {
            f.accept(this);
        }
        return null;
    }

    public Temp visit(ReturnStatement s) {
        //
        if (s.getExpression() != null) {
            //
            s.getExpression().accept(this);
        }
        //
        return null;
    }

    public Temp visit(StatementList sl) {
        for (ir.Statement s : sl.getStatements()) {
            s.accept(this);
        }
        return null;
    }

    public Temp visit(StringLiteral s) {
        //
        return null;
    }

    public Temp visit(SubtractExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Temp visit(Type t) {
        //
        return null;
    }

    public Temp visit(TypeNode t) {
        t.getType().accept(this);
        return null;
    }

    public Temp visit(VariableDeclaration v) {
        v.getDeclaration().accept(this);
        //
        return null;
    }

    public Temp visit(WhileStatement s) {
        //
        s.getExpression().accept(this);
        //
        s.getBlock().accept(this);
        return null;
    }
}