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
import environment.Environment;
import environment.StackHashMapEnvironment;
import ir.Temp.TempClass;
import java.util.ArrayList;
import java.util.List;
import type.ArrayType;
import type.BooleanType;
import type.CharType;
import type.FloatType;
import type.IntegerType;
import type.StringType;
import type.Type;
import type.VoidType;

public class IRVisitor implements Visitor<Temp> {
    private final Environment<String, Type> functionEnvironment;
    private final Environment<String, Temp> variableEnvironment;
    private TempFactory tempFactory;
    private List<Instruction> instructions;
    private List<Temp> expressionList;
    private LabelFactory labelFactory;
    private ir.Function currentFunction;
    private ir.Program program;

    public IRVisitor(String programName) {
        this.functionEnvironment = new StackHashMapEnvironment<String, Type>();
        this.variableEnvironment = new StackHashMapEnvironment<String, Temp>();
        this.labelFactory = new LabelFactory();
        this.program = new ir.Program(programName);
    }

    public ir.Program getProgram() {
        return this.program;
    }

    public Temp visit(AddExpression e) {
        Temp left = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Temp right = e.getRightExpression().accept(this);
            Type type = left.getType();
            Temp result = this.tempFactory.getTemp(type, TempClass.TEMP);
            Operand sum = new AddOperation(type, left, right);
            Instruction assignment = new AssignmentInstruction(result, sum);
            this.instructions.add(assignment);
            return result;
        }
        return left;
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

    public Temp visit(AssignmentStatement a) {
        a.getIdentifier().accept(this);
        String name = a.getIdentifier().getName();
        Temp operand1 = this.variableEnvironment.lookup(name);
        Temp operand2 = a.getExpression().accept(this);
        Instruction assignment = new AssignmentInstruction(operand1, operand2);
        this.instructions.add(assignment);
        return null;
    }

    public Temp visit(Block b) {
        b.getStatementList().accept(this);
        return null;
    }

    public Temp visit(BooleanLiteral b) {
        Temp temp = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
        Constant constant = new BooleanConstant(b.getValue());
        Instruction assignment = new AssignmentInstruction(temp, constant);
        this.instructions.add(assignment);
        return temp;
    }

    public Temp visit(CharacterLiteral c) {
        Temp temp = this.tempFactory.getTemp(new CharType(), TempClass.TEMP);
        Constant constant = new CharacterConstant(c.getValue());
        Instruction assignment = new AssignmentInstruction(temp, constant);
        this.instructions.add(assignment);
        return temp;
    }

    public Temp visit(Declaration d) {
        d.getType().accept(this);
        d.getIdentifier().accept(this);
        return null;
    }

    public Temp visit(EmptyStatement e) {
        return null;
    }

    public Temp visit(EqualityExpression e) {
        Temp left = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Temp right = e.getRightExpression().accept(this);
            Type type = left.getType();
            Temp result = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
            Operand equality = new EqualityOperation(type, left, right);
            Instruction assignment = new AssignmentInstruction(result, equality);
            this.instructions.add(assignment);
            return result;
        }
        return left;
    }

    public Temp visit(ExpressionList e) {
        List<Temp> temps = new ArrayList<Temp>();
        for (Expression expr : e.getExpressions()) {
            Temp temp = expr.accept(this);
            temps.add(temp);
        }
        this.expressionList = temps;
        return null;
    }

    public Temp visit(ExpressionStatement e) {
        e.getExpression().accept(this);
        return null;
    }

    public Temp visit(FloatLiteral f) {
        Temp temp = this.tempFactory.getTemp(new FloatType(), TempClass.TEMP);
        Constant constant = new FloatConstant(f.getValue());
        Instruction assignment = new AssignmentInstruction(temp, constant);
        this.instructions.add(assignment);
        return temp;
    }

    public Temp visit(FormalParameters p) {
        this.variableEnvironment.beginScope();
        for (Declaration d : p.getParameters()) {
            d.accept(this);
            Type type = d.getType().getType();
            Temp temp = this.tempFactory.getTemp(type, TempClass.PARAMETER);
            String name = d.getIdentifier().getName();
            this.variableEnvironment.add(name, temp);
        }
        return null;
    }

    public Temp visit(ast.Function f) {
        f.getFunctionDeclaration().accept(this);
        f.getFunctionBody().accept(this);
        return null;
    }

    public Temp visit(FunctionBody f) {
        for (VariableDeclaration vd : f.getVariableDeclarations()) {
            vd.accept(this);
        }
        f.getStatementList().accept(this);
        // Always insert an empty return instruction
        // at the end of a function body even if it's redundant
        Instruction returnInstruction = new ReturnInstruction();
        this.instructions.add(returnInstruction);
        this.currentFunction.setTempFactory(this.tempFactory);
        this.currentFunction.setInstructions(this.instructions);
        this.program.addFunction(this.currentFunction);
        this.variableEnvironment.endScope();
        return null;
    }

    public Temp visit(FunctionCall f) {
        f.getIdentifier().accept(this);
        f.getExpressionList().accept(this);
        String functionName = f.getIdentifier().getName();
        Type functionReturnType = this.functionEnvironment.lookup(functionName);
        Instruction functionCallInstruction =
            new FunctionCallInstruction(functionName, this.expressionList);
        if (functionReturnType.equals(new VoidType())) {
            this.instructions.add(functionCallInstruction);
            return null;
        } else {
            Temp temp = this.tempFactory.getTemp(functionReturnType, TempClass.TEMP);
            Operand functionCallOperation =
                new FunctionCallOperation(functionName, this.expressionList);
            Instruction assign = new AssignmentInstruction(temp, functionCallOperation);
            this.instructions.add(assign);
            return temp;
        }
    }

    public Temp visit(FunctionDeclaration f) {
        this.tempFactory = new TempFactory();
        this.instructions = new ArrayList<Instruction>();
        f.getDeclaration().accept(this);
        f.getFormalParameters().accept(this);
        String name = f.getDeclaration().getIdentifier().getName();
        Type returnType = f.getDeclaration().getType().getType();
        List<Type> parameterTypes = new ArrayList<Type>();
        for (Declaration d : f.getFormalParameters().getParameters()) {
            parameterTypes.add(d.getType().getType());
        }
        this.currentFunction = new ir.Function(name, returnType, parameterTypes);
        return null;
    }

    public Temp visit(Identifier i) {
        return null;
    }

    public Temp visit(IdentifierExpression i) {
        i.getIdentifier().accept(this);
        String name = i.getIdentifier().getName();
        if (this.variableEnvironment.inCurrentScope(name)) {
            return this.variableEnvironment.lookup(name);
        }
        return null;
    }

    public Temp visit(IfElseStatement i) {
        Label label1 = this.labelFactory.getLabel();
        Label label2 = this.labelFactory.getLabel();
        Temp expressionTemp = i.getExpression().accept(this);
        // Need new temp here if expressionTemp is a local variable
        // or function parameter so we don't mess up state
        if (expressionTemp.isParameterOrLocal()) {
            Temp temp = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
            Instruction assignment = new AssignmentInstruction(temp, expressionTemp);
            this.instructions.add(assignment);
            expressionTemp = temp;
        }
        Temp negatedExpression = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
        Operand negationOperation = new BooleanNegationOperation(expressionTemp);
        Instruction assignment = new AssignmentInstruction(negatedExpression, negationOperation);
        this.instructions.add(assignment);
        Instruction conditionalGoto = new ConditionalGotoInstruction(negatedExpression, label1);
        this.instructions.add(conditionalGoto);
        i.getIfBlock().accept(this);
        Instruction unconditionalGoto = new UnconditionalGotoInstruction(label2);
        this.instructions.add(unconditionalGoto);
        this.instructions.add(label1);
        i.getElseBlock().accept(this);
        this.instructions.add(label2);
        return null;
    }

    public Temp visit(IfStatement i) {
        Label label = this.labelFactory.getLabel();
        Temp expressionTemp = i.getExpression().accept(this);
        // Need new temp here if expressionTemp is a local variable
        // or function parameter so we don't mess up state
        if (expressionTemp.isParameterOrLocal()) {
            Temp temp = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
            Instruction assignment = new AssignmentInstruction(temp, expressionTemp);
            this.instructions.add(assignment);
            expressionTemp = temp;
        }
        Temp negatedExpression = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
        Operand negationOperation = new BooleanNegationOperation(expressionTemp);
        Instruction assignment = new AssignmentInstruction(negatedExpression, negationOperation);
        this.instructions.add(assignment);
        Instruction conditionalGoto = new ConditionalGotoInstruction(negatedExpression, label);
        this.instructions.add(conditionalGoto);
        i.getBlock().accept(this);
        this.instructions.add(label);
        return null;
    }

    public Temp visit(IntegerLiteral i) {
        Temp temp = this.tempFactory.getTemp(new IntegerType(), TempClass.TEMP);
        Constant constant = new IntegerConstant(i.getValue());
        Instruction assignment = new AssignmentInstruction(temp, constant);
        this.instructions.add(assignment);
        return temp;
    }

    public Temp visit(LessThanExpression e) {
        Temp left = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Temp right = e.getRightExpression().accept(this);
            Type type = left.getType();
            Temp result = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
            Operand lessThan = new LessThanOperation(type, left, right);
            Instruction assignment = new AssignmentInstruction(result, lessThan);
            this.instructions.add(assignment);
            return result;
        }
        return left;
    }

    public Temp visit(MultiplyExpression e) {
        Temp left = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Temp right = e.getRightExpression().accept(this);
            Type type = left.getType();
            Temp result = this.tempFactory.getTemp(type, TempClass.TEMP);
            Operand multiply = new MultiplyOperation(type, left, right);
            Instruction assignment = new AssignmentInstruction(result, multiply);
            this.instructions.add(assignment);
            return result;
        }
        return left;
    }

    public Temp visit(ParenthesisExpression p) {
        return p.getExpression().accept(this);
    }

    public Temp visit(PrintlnStatement s) {
        Temp temp = s.getExpression().accept(this);
        Type type = temp.getType();
        Instruction println = new PrintlnInstruction(type, temp);
        this.instructions.add(println);
        return null;
    }

    public Temp visit(PrintStatement s) {
        Temp temp = s.getExpression().accept(this);
        Type type = temp.getType();
        Instruction print = new PrintInstruction(type, temp);
        this.instructions.add(print);
        return null;
    }

    public Temp visit(ast.Program p) {
        this.functionEnvironment.beginScope();
        for (ast.Function f : p.getFunctions()) {
            String functionName =
                f.getFunctionDeclaration().getDeclaration().getIdentifier().getName();
            Type functionType =
                f.getFunctionDeclaration().getDeclaration().getType().getType();
            if (this.functionEnvironment.inCurrentScope(functionName)) {
                System.out.println("whoops");
            }
            this.functionEnvironment.add(functionName, functionType);
        }
        for (ast.Function f : p.getFunctions()) {
            f.accept(this);
        }
        this.functionEnvironment.endScope();
        return null;
    }

    public Temp visit(ReturnStatement s) {
        Temp temp = null;
        if (s.getExpression() != null) {
            temp = s.getExpression().accept(this);
        }
        Instruction returnInstruction = new ReturnInstruction(temp);
        this.instructions.add(returnInstruction);
        return null;
    }

    public Temp visit(StatementList sl) {
        for (ast.Statement s : sl.getStatements()) {
            s.accept(this);
        }
        return null;
    }

    public Temp visit(StringLiteral s) {
        Temp temp = this.tempFactory.getTemp(new StringType(), TempClass.TEMP);
        Constant constant = new StringConstant(s.getValue());
        Instruction assignment = new AssignmentInstruction(temp, constant);
        this.instructions.add(assignment);
        return temp;
    }

    public Temp visit(SubtractExpression e) {
        Temp left = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            Temp right = e.getRightExpression().accept(this);
            Type type = left.getType();
            Temp result = this.tempFactory.getTemp(type, TempClass.TEMP);
            Operand subtract = new SubtractOperation(type, left, right);
            Instruction assignment = new AssignmentInstruction(result, subtract);
            this.instructions.add(assignment);
            return result;
        }
        return left;
    }

    public Temp visit(TypeNode t) {
        return null;
    }

    public Temp visit(VariableDeclaration v) {
        v.getDeclaration().accept(this);
        Type type = v.getDeclaration().getType().getType();
        Temp temp = this.tempFactory.getTemp(type, TempClass.LOCAL);
        String name = v.getDeclaration().getIdentifier().getName();
        this.variableEnvironment.add(name, temp);
        if (type instanceof ArrayType) {
            Operand arrayInitialization = new ArrayInitialization((ArrayType)type);
            Instruction assignment = new AssignmentInstruction(temp, arrayInitialization);
            this.instructions.add(assignment);
        }
        return null;
    }

    public Temp visit(WhileStatement s) {
        Label loopLabel = this.labelFactory.getLabel();
        Label breakLabel = this.labelFactory.getLabel();
        this.instructions.add(loopLabel);
        Temp expressionTemp = s.getExpression().accept(this);
        // Need new temp here if expressionTemp is a local variable
        // or function parameter so we don't mess up state
        if (expressionTemp.isParameterOrLocal()) {
            Temp temp = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
            Instruction assignment = new AssignmentInstruction(temp, expressionTemp);
            this.instructions.add(assignment);
            expressionTemp = temp;
        }
        Temp negatedExpression = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
        Operand negationOperation = new BooleanNegationOperation(expressionTemp);
        Instruction assignment = new AssignmentInstruction(negatedExpression, negationOperation);
        this.instructions.add(assignment);
        Instruction conditionalGoto =
            new ConditionalGotoInstruction(negatedExpression, breakLabel);
        this.instructions.add(conditionalGoto);
        s.getBlock().accept(this);
        Instruction unconditionalGoto = new UnconditionalGotoInstruction(loopLabel);
        this.instructions.add(unconditionalGoto);
        this.instructions.add(breakLabel);
        return null;
    }
}
