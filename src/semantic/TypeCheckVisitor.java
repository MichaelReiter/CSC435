package semantic;

import java.util.List;
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

public class TypeCheckVisitor implements Visitor<Type> {
    private final Environment<String, FunctionDeclaration> functionEnvironment;
    private final Environment<String, Type> variableEnvironment;
    private String currentFunction;
    private Type currentFunctionReturnType;

    public TypeCheckVisitor() {
        this.functionEnvironment = new StackHashMapEnvironment<String, FunctionDeclaration>();
        this.variableEnvironment = new StackHashMapEnvironment<String, Type>();
    }

    public Type visit(AddExpression e) {
        Type leftExpressionType = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Type rightExpressionType = e.getRightExpression().accept(this);
            if (leftExpressionType.equals(rightExpressionType) && (
                leftExpressionType.equals(new IntegerType()) ||
                leftExpressionType.equals(new FloatType()) ||
                leftExpressionType.equals(new CharType()) ||
                leftExpressionType.equals(new StringType()))) {
                return leftExpressionType;
            } else {
                throw new SemanticException(
                    "Cannot add type " + leftExpressionType + " with type " + rightExpressionType + ".",
                    e.getLine(),
                    e.getOffset());
            }
        }
        return leftExpressionType;
    }

    public Type visit(ArrayAssignmentStatement s) {
        s.getArrayReference().accept(this);
        String variableName = s.getArrayReference().getIdentifier().getName();
        Type variableType = this.variableEnvironment.lookup(variableName);
        variableType = ((ArrayType)variableType).getType();
        Type expressionType = s.getExpression().accept(this);
        if (!variableType.equals(expressionType)) {
            throw new SemanticException(
                "Variable " + variableName + " must be assigned an expression of type "
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
        if (this.variableEnvironment.inCurrentScope(variableName)) {
            Type arrayPrimitiveType = this.variableEnvironment.lookup(variableName);
            if (arrayPrimitiveType instanceof ArrayType) {
                arrayPrimitiveType = ((ArrayType)arrayPrimitiveType).getType();
            }
            return arrayPrimitiveType;
        } else {
            throw new SemanticException("Variable " + variableName + " is undeclared.",
                a.getLine(),
                a.getOffset());
        }
    }

    public Type visit(ArrayReferenceExpression a) {
        return a.getArrayReference().accept(this);
    }

    public Type visit(AssignmentStatement a) {
        a.getIdentifier().accept(this);
        String variableName = a.getIdentifier().getName();
        if (!this.variableEnvironment.inCurrentScope(variableName)) {
            throw new SemanticException("Variable " + variableName + " is undeclared.",
                a.getLine(),
                a.getOffset());
        }
        Type variableType = this.variableEnvironment.lookup(variableName);
        Type expressionType = a.getExpression().accept(this);
        if (!variableType.equals(expressionType)) {
            throw new SemanticException(
                "Variable " + variableName + " must be assigned an expression of type "
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
        // Nothing to do here
        return null;
    }

    public Type visit(EqualityExpression e) {
        Type leftExpressionType = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Type rightExpressionType = e.getRightExpression().accept(this);
            if (leftExpressionType.equals(rightExpressionType) && (
                leftExpressionType.equals(new IntegerType()) ||
                leftExpressionType.equals(new FloatType()) ||
                leftExpressionType.equals(new CharType()) ||
                leftExpressionType.equals(new StringType()) ||
                leftExpressionType.equals(new BooleanType()))) {
                return new BooleanType();
            } else {
                throw new SemanticException(
                    "Cannot compare type " + leftExpressionType + " with type " + rightExpressionType + ".",
                    e.getLine(),
                    e.getOffset());
            }
        }
        return leftExpressionType;
    }

    public Type visit(ExpressionList e) {
        // Handled by FunctionCall
        return null;
    }

    public Type visit(ExpressionStatement e) {
        return e.getExpression().accept(this);
    }

    public Type visit(FloatLiteral f) {
        return new FloatType();
    }

    public Type visit(FormalParameters p) {
        this.variableEnvironment.beginScope();
        for (Declaration d : p.getParameters()) {
            String variableName = d.getIdentifier().getName();
            if (this.variableEnvironment.inCurrentScope(variableName)) {
                throw new SemanticException(
                    "Variable " + variableName + " already exists.",
                    p.getLine(),
                    p.getOffset());
            }
            Type variableType = d.accept(this);
            if (variableType.equals(new VoidType())) {
                throw new SemanticException(
                    "Cannot declare formal parameters of type void.",
                    p.getLine(),
                    p.getOffset());
            }
            this.variableEnvironment.add(variableName, variableType);
        }
        return null;
    }

    public Type visit(Function f) {
        f.getFunctionDeclaration().accept(this);
        f.getFunctionBody().accept(this);
        return null;
    }

    public Type visit(FunctionBody f) {
        for (VariableDeclaration vd : f.getVariableDeclarations()) {
            vd.accept(this);
        }
        f.getStatementList().accept(this);
        this.variableEnvironment.endScope();
        return null;
    }

    public Type visit(FunctionCall f) {
        String functionName = f.getIdentifier().getName();
        if (this.functionEnvironment.inCurrentScope(functionName)) {
            FunctionDeclaration functionDeclaration = this.functionEnvironment.lookup(functionName);
            f.getIdentifier().accept(this);
            List<Declaration> formalParameters = functionDeclaration.getFormalParameters().getParameters();
            List<Expression> arguments = f.getExpressionList().getExpressions();
            if (formalParameters.size() != arguments.size()) {
                throw new SemanticException("Called function " + functionName + " with " + arguments.size()
                    + " argument(s). Expected " + formalParameters.size() + ".",
                    f.getLine(),
                    f.getOffset());
            }
            for (int i = 0; i < arguments.size(); i++) {
                Type argumentType = arguments.get(i).accept(this);
                Type parameterType = formalParameters.get(i).getType().getType();
                if (!argumentType.equals(parameterType)) {
                    throw new SemanticException(
                        "Called function " + functionName + " with invalid arguments. Formal parameter and"
                            + " argument types do not match.",
                        f.getLine(),
                        f.getOffset());
                }
            }
            f.getExpressionList().accept(this);
            Type functionType = functionDeclaration.getDeclaration().accept(this);
            return functionType;
        } else {
            throw new SemanticException("Function " + functionName + " does not exist. "
                + "Functions must be defined before they are invoked.",
                f.getLine(),
                f.getOffset());
        }
    }

    public Type visit(FunctionDeclaration f) {
        Type functionType = f.getDeclaration().accept(this);
        String functionName = f.getDeclaration().getIdentifier().getName();
        this.currentFunction = functionName;
        this.currentFunctionReturnType = functionType;
        f.getFormalParameters().accept(this);
        return null;
    }

    public Type visit(Identifier i) {
        // Nothing to do here
        return null;
    }

    public Type visit(IdentifierExpression i) {
        i.getIdentifier().accept(this);
        String variableName = i.getIdentifier().getName();
        if (this.variableEnvironment.inCurrentScope(variableName)) {
            return this.variableEnvironment.lookup(variableName);
        } else {
            throw new SemanticException("Variable " + variableName + " is undeclared.",
                i.getLine(),
                i.getOffset());
        }
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
        Type leftExpressionType = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Type rightExpressionType = e.getRightExpression().accept(this);
            if (leftExpressionType.equals(rightExpressionType) && (
                leftExpressionType.equals(new IntegerType()) ||
                leftExpressionType.equals(new FloatType()) ||
                leftExpressionType.equals(new CharType()) ||
                leftExpressionType.equals(new StringType()) ||
                leftExpressionType.equals(new BooleanType()))) {
                return new BooleanType();
            } else {
                throw new SemanticException(
                    "Cannot compare type " + leftExpressionType + " with type " + rightExpressionType + ".",
                    e.getLine(),
                    e.getOffset());
            }
        }
        return leftExpressionType;
    }

    public Type visit(MultiplyExpression e) {
        Type leftExpressionType = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Type rightExpressionType = e.getRightExpression().accept(this);
            if (leftExpressionType.equals(rightExpressionType) && (
                leftExpressionType.equals(new IntegerType()) ||
                leftExpressionType.equals(new FloatType()))) {
                return leftExpressionType;
            } else {
                throw new SemanticException(
                    "Cannot multiply type " + leftExpressionType + " with type " + rightExpressionType + ".",
                    e.getLine(),
                    e.getOffset());
            }
        }
        return leftExpressionType;
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
        this.functionEnvironment.beginScope();
        // Two pass to add functions to functionEnvironment before accepting
        // so they can be arbitrarily ordered in the program
        for (Function f : p.getFunctions()) {
            FunctionDeclaration functionDeclaration = f.getFunctionDeclaration();
            String functionName = functionDeclaration.getDeclaration().getIdentifier().getName();
            if (this.functionEnvironment.inCurrentScope(functionName)) {
                throw new SemanticException("Function " + functionName + " already exists.",
                    functionDeclaration.getLine(),
                    functionDeclaration.getOffset());
            }
            this.functionEnvironment.add(functionName, functionDeclaration);
        }
        for (Function f : p.getFunctions()) {
            f.accept(this);
        }
        if (this.functionEnvironment.sizeOfCurrentScope() == 0) {
            throw new SemanticException(
                "Programs must contain at least one function.",
                p.getLine(),
                p.getOffset());
        }
        if (!this.functionEnvironment.inCurrentScope("main")) {
            throw new SemanticException(
                "Main function does not exist.",
                p.getLine(),
                p.getOffset());
        }
        FunctionDeclaration mainFunctionDeclaration = this.functionEnvironment.lookup("main");
        if (!mainFunctionDeclaration.getDeclaration().getType().getType().equals(new VoidType())) {
            throw new SemanticException(
                "Main function must return type void.",
                mainFunctionDeclaration.getLine(),
                mainFunctionDeclaration.getOffset());
        }
        if (mainFunctionDeclaration.getFormalParameters().size() != 0) {
            throw new SemanticException(
                "Main function must have no formal parameters.",
                mainFunctionDeclaration.getLine(),
                mainFunctionDeclaration.getOffset());
        }
        this.functionEnvironment.endScope();
        return null;
    }

    public Type visit(ReturnStatement s) {
        if (this.currentFunctionReturnType.equals(new VoidType())) {
            if (s.getExpression() != null) {
                throw new SemanticException(
                    "Function " + currentFunction + " is of type void, thus must not return anything.",
                    s.getLine(),
                    s.getOffset());
            }
        } else if (s.getExpression() == null) {
            throw new SemanticException(
                "Function " + currentFunction + " must return type " + currentFunctionReturnType + ".",
                s.getLine(),
                s.getOffset());
        } else {
            Type expressionType = s.getExpression().accept(this);
            if (!expressionType.equals(this.currentFunctionReturnType)) {
                throw new SemanticException(
                    "Function " + currentFunction + " must return type " + currentFunctionReturnType + "."
                        + " Found " + expressionType + ".",
                    s.getLine(),
                    s.getOffset());
            }
        }
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
        Type leftExpressionType = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Type rightExpressionType = e.getRightExpression().accept(this);
            if (leftExpressionType.equals(rightExpressionType) && (
                leftExpressionType.equals(new IntegerType()) ||
                leftExpressionType.equals(new FloatType()) ||
                leftExpressionType.equals(new CharType()))) {
                return leftExpressionType;
            } else {
                throw new SemanticException(
                    "Cannot subtract type " + leftExpressionType + " with type " + rightExpressionType + ".",
                    e.getLine(),
                    e.getOffset());
            }
        }
        return leftExpressionType;
    }

    public Type visit(TypeNode t) {
        return t.getType();
    }

    public Type visit(VariableDeclaration v) {
        String variableName = v.getDeclaration().getIdentifier().getName();
        if (this.variableEnvironment.inCurrentScope(variableName)) {
            throw new SemanticException(
                "Variable " + variableName + " already exists.",
                v.getLine(),
                v.getOffset());
        }
        Type variableType = v.getDeclaration().accept(this);
        if (variableType.equals(new VoidType())) {
            throw new SemanticException(
                "Cannot declare a variable of type void.",
                v.getLine(),
                v.getOffset());
        }
        this.variableEnvironment.add(variableName, variableType);
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
