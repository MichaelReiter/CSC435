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

public class IRVisitor implements Visitor<Temp> {
    private final Environment<String, Type> functionEnvironment;
    private final Environment<String, Temp> variableEnvironment;
    private TempFactory tempFactory;
    private List<Instruction> instructions;
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
            Temp result = this.tempFactory.getTemp(left.getType(), TempClass.TEMP);
            Type type = left.getType();
            Operand sum = new AddOperation(type, left, right);
            Instruction add = new AssignmentInstruction(result, sum);
            this.instructions.add(add);
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
        Instruction assignmentInstruction = new AssignmentInstruction(operand1, operand2);
        this.instructions.add(assignmentInstruction);
        return null;
    }

    public Temp visit(Block b) {
        b.getStatementList().accept(this);
        return null;
    }

    public Temp visit(BooleanLiteral b) {
        Temp temp = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
        Constant constant = new BooleanConstant(b.getValue());
        Instruction literal = new AssignmentInstruction(temp, constant);
        this.instructions.add(literal);
        return temp;
    }

    public Temp visit(CharacterLiteral c) {
        Temp temp = this.tempFactory.getTemp(new CharType(), TempClass.TEMP);
        Constant constant = new CharacterConstant(c.getValue());
        Instruction literal = new AssignmentInstruction(temp, constant);
        this.instructions.add(literal);
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
            //
            e.getRightExpression().accept(this);
        }
        return left;
    }

    public Temp visit(ExpressionList e) {
        for (Expression expr : e.getExpressions()) {
            expr.accept(this);
        }
        return null;
    }

    public Temp visit(ExpressionStatement e) {
        e.getExpression().accept(this);
        //
        return null;
    }

    public Temp visit(FloatLiteral f) {
        Temp temp = this.tempFactory.getTemp(new FloatType(), TempClass.TEMP);
        Constant constant = new FloatConstant(f.getValue());
        Instruction literal = new AssignmentInstruction(temp, constant);
        this.instructions.add(literal);
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
        //
        for (VariableDeclaration vd : f.getVariableDeclarations()) {
            vd.accept(this);
        }
        if (f.size() > 0) {
            //
        }
        f.getStatementList().accept(this);
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
        List<Temp> functionArguments = new ArrayList<Temp>();
        Instruction callInstruction = new CallInstruction(functionName, functionArguments);
        this.instructions.add(callInstruction);
        return null;
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
        // for (Type t : parameterTypes) {
        //     this.tempFactory.getTemp(t, TempClass.PARAMETER);
        // }
        this.currentFunction = new ir.Function(name, returnType, parameterTypes);
        return null;
    }

    public Temp visit(Identifier i) {
        //
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
        // //
        // i.getExpression().accept(this);
        // //
        // i.getIfBlock().accept(this);
        // //
        // i.getElseBlock().accept(this);
        // return null;

        // From Corless example in class
        // Instruction i1;
        // Label label1 = this.labelFactory.getLabel();
        // Label label2 = this.labelFactory.getLabel();
        // Temp temp1 = i.getExpression().accept(this);
        // // need new temp here if i1 is LOCAL or PARAMETER so we don't mess up state
        // if (temp1.isParameterOrLocal()) {
        //     Temp temp2 = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
        //     i1 = new AssignmentInstruction(temp2, temp1);
        //     this.instructions.add(i1);
        //     temp1 = temp2;
        // }
        // i1 = new BooleanNegationOperation(temp1);
        // this.instructions.add(i1);

        // Instruction conditionalGotoInstruction = new ConditionalGotoInstruction(temp1, label1);
        // this.instructions.add(conditionalGotoInstruction);

        // // this.tempFactory.returnTemp(temp1);

        // i.getIfBlock().accept(this);

        // Instruction unconditionalGotoInstruction = new UnconditionalGotoInstruction(label2);
        // this.instructions.add(unconditionalGotoInstruction);

        // this.instructions.add(label1);

        // i.getElseBlock().accept(this);
        // this.instructions.add(label2);
        return null;
    }

    public Temp visit(IfStatement i) {
        // //
        // i.getExpression().accept(this);
        // //
        // i.getBlock().accept(this);
        // return null;

        // From Corless example in class
        // Instruction i1;
        // Label label1 = this.labelFactory.getLabel();
        // Label label2 = this.labelFactory.getLabel();
        // Temp temp1 = i.getExpression().accept(this);
        // // need new temp here if i1 is LOCAL or PARAMETER so we don't mess up state
        // if (temp1.isParameterOrLocal()) {
        //     Temp temp2 = this.tempFactory.getTemp(new BooleanType(), TempClass.TEMP);
        //     i1 = new AssignmentInstruction(temp2, temp1);
        //     this.instructions.add(i1);
        //     temp1 = temp2;
        // }
        // i1 = new BooleanNegationOperation(temp1);
        // this.instructions.add(i1);

        // Instruction conditionalGotoInstruction = new ConditionalGotoInstruction(temp1, label1);
        // this.instructions.add(conditionalGotoInstruction);

        // // this.tempFactory.returnTemp(temp1);

        // i.getBlock().accept(this);

        // Instruction unconditionalGotoInstruction = new UnconditionalGotoInstruction(label2);
        // this.instructions.add(unconditionalGotoInstruction);

        // this.instructions.add(label1);

        // this.instructions.add(label2);
        return null;
    }

    public Temp visit(IntegerLiteral i) {
        Temp temp = this.tempFactory.getTemp(new IntegerType(), TempClass.TEMP);
        Constant constant = new IntegerConstant(i.getValue());
        Instruction literal = new AssignmentInstruction(temp, constant);
        this.instructions.add(literal);
        return temp;
    }

    public Temp visit(LessThanExpression e) {
        Temp left = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return left;
    }

    public Temp visit(MultiplyExpression e) {
        Temp left = e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return left;
    }

    public Temp visit(ParenthesisExpression p) {
        return p.getExpression().accept(this);
    }

    public Temp visit(PrintlnStatement s) {
        Temp temp = s.getExpression().accept(this);
        Type type = temp.getType();
        Instruction printlnInstruction = new PrintlnInstruction(type, temp);
        this.instructions.add(printlnInstruction);
        return null;
    }

    public Temp visit(PrintStatement s) {
        Temp temp = s.getExpression().accept(this);
        Type type = temp.getType();
        Instruction printInstruction = new PrintInstruction(type, temp);
        this.instructions.add(printInstruction);
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
        Instruction literal = new AssignmentInstruction(temp, constant);
        this.instructions.add(literal);
        return temp;
    }

    public Temp visit(SubtractExpression e) {
        e.getLeftExpression().accept(this);
        if (e.getRightExpression() != null) {
            //
            e.getRightExpression().accept(this);
        }
        return null;
    }

    public Temp visit(TypeNode t) {
        //
        return null;
    }

    public Temp visit(VariableDeclaration v) {
        v.getDeclaration().accept(this);
        Type type = v.getDeclaration().getType().getType();
        Temp temp = this.tempFactory.getTemp(type, TempClass.LOCAL);
        String name = v.getDeclaration().getIdentifier().getName();
        this.variableEnvironment.add(name, temp);
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
