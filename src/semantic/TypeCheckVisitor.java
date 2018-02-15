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
import environment.Environment;
import environment.StackHashMapEnvironment;
import type.ArrayType;
import type.BooleanType;
import type.CharType;
import type.FloatType;
import type.IntegerType;
import type.StringType;
import type.Type;
import type.VoidType;

public class TypeCheckVisitor implements Visitor {
    private final Environment<String, FunctionDeclaration> functionEnvironment;
    private final Environment<String, Type> variableEnvironment;
    private String currentFunction;
    private Type currentFunctionReturnType;

    public TypeCheckVisitor() {
        this.functionEnvironment = new StackHashMapEnvironment<String, FunctionDeclaration>();
        this.variableEnvironment = new StackHashMapEnvironment<String, Type>();
    }

    public Type visit(AddExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(ArrayAssignmentStatement s) {
        Type variableType = s.getArrayReference().getIdentifier().accept(this);
        Type expressionType = s.getExpression().accept(this);
        if (!variableType.equals(expressionType)) {
            String name = s.getArrayReference().getIdentifier().getName();
            throw new SemanticException(
                "Variable " + name + " must be assigned an expression of type "
                    + variableType + ". Found " + expressionType + ".",
                s.getLine(),
                s.getOffset());
        }

        return null;
    }

    public Type visit(ArrayReference a) {
        String variableName = a.getIdentifier().getName();
        Type expressionType = a.getExpression().accept(this);
        if (!expressionType.equals(new IntegerType())) {
            throw new SemanticException(
                "Array index expression must be of type int. Found " + expressionType + ".",
                a.getLine(),
                a.getOffset());
        }
        return variableEnvironment.lookup(variableName);
    }

    public Type visit(ArrayReferenceExpression a) {
        return a.getArrayReference().accept(this);
    }

    public Type visit(ArrayType a) {
        return a.getType();
    }

    public Type visit(AssignmentStatement a) {
        Type variableType = a.getIdentifier().accept(this);
        Type expressionType = a.getExpression().accept(this);
        if (!variableType.equals(expressionType)) {
            String name = a.getIdentifier().getName();
            throw new SemanticException(
                "Variable " + name + " must be assigned an expression of type "
                    + variableType + ". Found " + expressionType + ".",
                a.getLine(),
                a.getOffset());
        }
        return null;
    }

    public Type visit(Block b) {
        b.getStatementList().accept(this);
        return null;
    }

    public Type visit(BooleanLiteral b) {
        return new BooleanType();
    }

    public Type visit(CharacterLiteral c) {
        return new CharType();
    }

    public Type visit(Declaration d) {
        Type declarationType = d.getType().accept(this);
        d.getIdentifier().accept(this);
        return declarationType;
    }

    public Type visit(EmptyStatement e) {
        return null;
    }

    public Type visit(EqualityExpression e) {
        Type expressionType = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return expressionType;
    }

    public Type visit(ExpressionList e) {
        for (int i = 0; i < e.size(); i++) {
            e.get(i).accept(this);
        }
        return null;
    }

    public Type visit(ExpressionStatement e) {
        return e.getExpression().accept(this);
    }

    public Type visit(FloatLiteral f) {
        return new FloatType();
    }

    // TODO: typecheck function declaration and ivocation parameters
    public Type visit(FormalParameters p) {
        for (int i = 0; i < p.size(); i++) {
            p.get(i).accept(this);
        }
        return null;
    }

    public Type visit(Function f) {
        f.getFunctionDeclaration().accept(this);
        f.getFunctionBody().accept(this);
        return null;
    }

    public Type visit(FunctionBody f) {
        variableEnvironment.beginScope();
        //
        for (VariableDeclaration vd : f.getVariableDeclarations()) {
            vd.accept(this);
        }
        if (f.size() > 0) {
            //
        }
        f.getStatementList().accept(this);
        //
        variableEnvironment.endScope();
        return null;
    }

    public Type visit(FunctionCall f) {
        String functionName = f.getIdentifier().getName();
        if (functionEnvironment.inCurrentScope(functionName)) {
            FunctionDeclaration functionDeclaration = functionEnvironment.lookup(functionName);
            Type functionType = functionDeclaration.getDeclaration().accept(this);
            f.getIdentifier().accept(this);
            f.getExpressionList().accept(this);
            return functionType;
        } else {
            throw new SemanticException("Function " + functionName + " does not exist. "
                + "Functions must be defined before they are invoked.",
                f.getLine(),
                f.getOffset());
        }
    }

    public Type visit(FunctionDeclaration f) {
        String functionName = f.getDeclaration().getIdentifier().getName();
        if (functionEnvironment.inCurrentScope(functionName)) {
            throw new SemanticException("Function " + functionName + " already exists.",
                f.getLine(),
                f.getOffset());
        }
        functionEnvironment.add(functionName, f);
        f.getFormalParameters().accept(this);
        return null;
    }

    public Type visit(Identifier i) {
        // this might cause bugs later when looking up functions...
        return variableEnvironment.lookup(i.getName());
    }

    public Type visit(IdentifierExpression i) {
        return i.getIdentifier().accept(this);
    }

    public Type visit(IfElseStatement i) {
        Type expressionType = i.getExpression().accept(this);
        if (!expressionType.equals(new BooleanType())) {
            throw new SemanticException(
                "Expression in if statement must be of type boolean. Found " + expressionType + ".",
                i.getLine(),
                i.getOffset());
        }
        i.getIfBlock().accept(this);
        if (i.getElseBlock() != null) {
            i.getElseBlock().accept(this);
        }
        return null;
    }

    public Type visit(IfStatement i) {
        Type expressionType = i.getExpression().accept(this);
        if (!expressionType.equals(new BooleanType())) {
            throw new SemanticException(
                "Expression in if statement must be of type boolean. Found " + expressionType + ".",
                i.getLine(),
                i.getOffset());
        }
        i.getBlock().accept(this);
        return null;
    }

    public Type visit(IntegerLiteral i) {
        return new IntegerType();
    }

    public Type visit(LessThanExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(MultiplyExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(ParenthesisExpression p) {
        return p.getExpression().accept(this);
    }

    public Type visit(PrintlnStatement s) {
        Type expressionType = s.getExpression().accept(this);
        if (!(expressionType.equals(new IntegerType()) || 
            expressionType.equals(new FloatType()) || 
            expressionType.equals(new CharType()) || 
            expressionType.equals(new StringType()) || 
            expressionType.equals(new BooleanType()))) {
            throw new SemanticException(
                "Expression in println statement cannot be of type " + expressionType + ".",
                s.getLine(),
                s.getOffset());
        }
        return null;
    }

    public Type visit(PrintStatement s) {
        Type expressionType = s.getExpression().accept(this);
        if (!(expressionType.equals(new IntegerType()) || 
            expressionType.equals(new FloatType()) || 
            expressionType.equals(new CharType()) || 
            expressionType.equals(new StringType()) || 
            expressionType.equals(new BooleanType()))) {
            throw new SemanticException(
                "Expression in print statement cannot be of type " + expressionType + ".",
                s.getLine(),
                s.getOffset());
        }
        return null;
    }

    public Type visit(Program p) {
        functionEnvironment.beginScope();
        for (Function f : p.getFunctions()) {
            f.accept(this);
        }
        functionEnvironment.endScope();
        return null;
    }

    public Type visit(ReturnStatement s) {
        // TODO type check function returns
        if (s.getExpression() != null) {
            //
            s.getExpression().accept(this);
        }
        //
        return null;
    }

    public Type visit(StatementList sl) {
        for (ast.Statement s : sl.getStatements()) {
            s.accept(this);
        }
        return null;
    }

    public Type visit(StringLiteral s) {
        return new StringType();
    }

    public Type visit(SubtractExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Type visit(Type t) {
        return t;
    }

    public Type visit(TypeNode t) {
        return t.getType().accept(this);
    }

    public Type visit(VariableDeclaration v) {
        String variableName = v.getDeclaration().getIdentifier().getName();
        if (variableEnvironment.inCurrentScope(variableName)) {
            throw new SemanticException(
                "Variable " + variableName + " already exists.",
                v.getLine(),
                v.getOffset());
        }
        Type variableType = v.getDeclaration().accept(this);
        variableEnvironment.add(variableName, variableType);
        return null;
    }

    public Type visit(WhileStatement s) {
        Type expressionType = s.getExpression().accept(this);
        if (!expressionType.equals(new BooleanType())) {
            throw new SemanticException(
                "Expression in while statement must be of type boolean. Found " + expressionType + ".",
                s.getLine(),
                s.getOffset());
        }
        s.getBlock().accept(this);
        return null;
    }
}
