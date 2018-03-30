package codegen;

import ir.AddOperation;
import ir.ArrayInitialization;
import ir.ArrayReference;
import ir.AssignmentInstruction;
import ir.BooleanConstant;
import ir.BooleanNegationOperation;
import ir.CharacterConstant;
import ir.ConditionalGotoInstruction;
import ir.EqualityOperation;
import ir.FloatConstant;
import ir.Function;
import ir.FunctionCallInstruction;
import ir.FunctionCallOperation;
import ir.IntegerConstant;
import ir.Instruction;
import ir.Label;
import ir.LessThanOperation;
import ir.MultiplyOperation;
import ir.NewArrayOperand;
import ir.Operand;
import ir.PrintInstruction;
import ir.PrintlnInstruction;
import ir.Program;
import ir.ReturnInstruction;
import ir.StringConstant;
import ir.SubtractOperation;
import ir.Temp;
import ir.TempFactory;
import ir.UnconditionalGotoInstruction;
import ir.Temp.TempClass;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import type.ArrayType;
import type.BooleanType;
import type.CharType;
import type.FloatType;
import type.IntegerType;
import type.StringType;
import type.Type;

public class JasminVisitor implements CodeGenVisitor {
    private final Program program;
    private final StringBuilder stringBuilder;
    private int labelNumber;

    public JasminVisitor(Program program) {
        this.program = program;
        this.stringBuilder = new StringBuilder();
        this.labelNumber = 0;
    }

    public void visit(AddOperation a) {
        Type type = a.getType();
        int leftNumber = a.getLeftOperand().getNumber();
        int rightNumber = a.getRightOperand().getNumber();
        // Integer
        if (type.equals(new IntegerType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tiadd\n");
        // Float
        } else if (type.equals(new FloatType())) {
            this.stringBuilder.append("\tfload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tfload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tfadd\n");
        // Character
        } else if (type.equals(new CharType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tiadd\n");
            this.stringBuilder.append("\ti2c\n");
        // String
        } else if (type.equals(new StringType())) {
            this.stringBuilder.append("\tnew java/lang/StringBuffer\n");
            this.stringBuilder.append("\tdup\n");
            this.stringBuilder.append("\tinvokenonvirtual java/lang/StringBuffer/<init>()V\n");
            this.stringBuilder.append("\taload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tinvokevirtual ");
            this.stringBuilder.append(
                "java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;\n");
            this.stringBuilder.append("\taload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tinvokevirtual ");
            this.stringBuilder.append(
                "java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;\n");
            this.stringBuilder.append(
                "\tinvokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;\n");
        } else {
            this.assertError();
        }
    }

    public void visit(ArrayInitialization a) {
        
    }

    private void visitArrayAssignment(Temp left, Operand right) {
        this.stringBuilder.append("\taload ");
        this.stringBuilder.append(((ArrayReference)left).getIdentifier().getNumber());
        Type type = left.getType();
        // Integer
        if (type.equals(new IntegerType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());
            this.stringBuilder.append("\n");
            right.accept(this);
            this.stringBuilder.append("\tiastore\n");
        // Float
        } else if (type.equals(new FloatType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());
            this.stringBuilder.append("\n");            
            right.accept(this);
            this.stringBuilder.append("\tfastore\n");
        // Character
        } else if (type.equals(new CharType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());
            this.stringBuilder.append("\n");            
            right.accept(this);
            this.stringBuilder.append("\tcastore\n");
        // String
        } else if (type.equals(new StringType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());
            this.stringBuilder.append("\n");            
            right.accept(this);
            this.stringBuilder.append("\taastore\n");
        // Boolean
        } else if (type.equals(new BooleanType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());
            this.stringBuilder.append("\n");        
            right.accept(this);
            this.stringBuilder.append("\tbastore\n");
        } else {
            this.assertError();
        }
    }

    private void visitArrayDeclaration(Temp left) {
        this.stringBuilder.append("\tldc ");
        Type type = left.getType();
        this.stringBuilder.append(((ArrayType)type).getSize());
        // Integer
        if (((ArrayType)type).getType().equals(new IntegerType())) {
            this.stringBuilder.append("\n\tnewarray int\n");
        // Float
        } else if (((ArrayType)type).getType().equals(new FloatType())) {
            this.stringBuilder.append("\n\tnewarray float\n");
        // Character
        } else if (((ArrayType)type).getType().equals(new CharType())) {
            this.stringBuilder.append("\n\tnewarray char\n");
        // String
        } else if (((ArrayType)type).getType().equals(new StringType())) {
            this.stringBuilder.append("\n\tanewarray java/lang/String\n");
        // Boolean
        } else if (((ArrayType)type).getType().equals(new BooleanType())) {
            this.stringBuilder.append("\n\tnewarray boolean\n");
        }
        this.stringBuilder.append("\tastore ");
        this.stringBuilder.append(left.getNumber());
        this.stringBuilder.append("\n");
    }

    private void visitVariableAssignment(Temp left, Operand right) {
        // Integer
        if (left.getType().equals(new IntegerType())) {
            right.accept(this);
            this.stringBuilder.append("\tistore ");
        // Float
        } else if (left.getType().equals(new FloatType())) {
            right.accept(this);
            this.stringBuilder.append("\tfstore ");
        // Character
        } else if (left.getType().equals(new CharType())) {
            right.accept(this);
            this.stringBuilder.append("\tistore ");
        // String
        } else if (left.getType().equals(new StringType())) {
            right.accept(this);
            this.stringBuilder.append("\tastore ");
        // Boolean
        } else if (left.getType().equals(new BooleanType())) {
            right.accept(this);
            this.stringBuilder.append("\tistore ");
        } else {
            this.assertError();
        }
        this.stringBuilder.append(left.getNumber());
        this.stringBuilder.append("\n");
    }

    public void visit(AssignmentInstruction a) {
        Temp left = a.getLeftOperand();
        Operand right = a.getRightOperand();
        if (left.getType() instanceof ArrayType) {
            this.visitArrayDeclaration(left);
        } else if (left instanceof ArrayReference) {
            this.visitArrayAssignment(left, right);
        } else {
            this.visitVariableAssignment(left, right);
        }
    }

    public void visit(BooleanConstant b) {
        this.stringBuilder.append("\tldc ");
        this.stringBuilder.append(b.getValue() ? 1 : 0);
        this.stringBuilder.append("\n");
    }

    public void visit(BooleanNegationOperation b) {
        this.stringBuilder.append("\tiload ");
        this.stringBuilder.append(b.getOperand().getNumber());
        this.stringBuilder.append("\n\tldc 1\n");
        this.stringBuilder.append("\tixor\n");
    }

    public void visit(CharacterConstant c) {
        this.stringBuilder.append("\tldc ");
        this.stringBuilder.append((int)c.getValue());
        this.stringBuilder.append("\n");
    }

    public void visit(ConditionalGotoInstruction c) {
        this.stringBuilder.append("\tiload ");
        this.stringBuilder.append(c.getCondition().getNumber());
        this.stringBuilder.append("\n\tifne L");
        this.stringBuilder.append(c.getLabel().getNumber());
        this.stringBuilder.append("\n");
    }

    public void visit(EqualityOperation e) {
        Type type = e.getType();
        int leftNumber = e.getLeftOperand().getNumber();
        int rightNumber = e.getRightOperand().getNumber();
        // Integer
        if (type.equals(new IntegerType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tisub\n");
            this.stringBuilder.append("\tifeq L_");
            this.stringBuilder.append(this.labelNumber);
            this.labelNumber++;
            this.stringBuilder.append("\n\tldc 0\n");
            this.stringBuilder.append("\tgoto L_");
            this.stringBuilder.append(this.labelNumber);
            this.labelNumber++;
            this.stringBuilder.append("\nL_");
            this.stringBuilder.append(this.labelNumber - 2);
            this.stringBuilder.append(":\n");
            this.stringBuilder.append("\tldc 1\n");
            this.stringBuilder.append("L_");
            this.stringBuilder.append(this.labelNumber - 1);
            this.stringBuilder.append(":\n");
        // Float
        } else if (type.equals(new FloatType())) {
            this.stringBuilder.append("\tfload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tfload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tfcmpg\n");
            this.stringBuilder.append("\tifeq L_");
            this.stringBuilder.append(this.labelNumber);
            this.labelNumber++;
            this.stringBuilder.append("\n\tldc 0\n");
            this.stringBuilder.append("\tgoto L_");
            this.stringBuilder.append(this.labelNumber);
            this.labelNumber++;
            this.stringBuilder.append("\nL_");
            this.stringBuilder.append(this.labelNumber - 2);
            this.stringBuilder.append(":\n");
            this.stringBuilder.append("\tldc 1\n");
            this.stringBuilder.append("L_");
            this.stringBuilder.append(this.labelNumber - 1);
            this.stringBuilder.append(":\n");
        // Character
        } else if (type.equals(new CharType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tisub\n");
            this.stringBuilder.append("\tifeq L_");
            this.stringBuilder.append(this.labelNumber);
            this.labelNumber++;
            this.stringBuilder.append("\n\tldc 0\n");
            this.stringBuilder.append("\tgoto L_");
            this.stringBuilder.append(this.labelNumber);
            this.labelNumber++;
            this.stringBuilder.append("\nL_");
            this.stringBuilder.append(this.labelNumber - 2);
            this.stringBuilder.append(":\n");
            this.stringBuilder.append("\tldc 1\n");
            this.stringBuilder.append("L_");
            this.stringBuilder.append(this.labelNumber - 1);
            this.stringBuilder.append(":\n");
        // String
        } else if (type.equals(new StringType())) {
            this.stringBuilder.append("\taload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\taload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append(
                "\n\tinvokevirtual java/lang/String/compareTo(Ljava/lang/String;)I\n");
            this.stringBuilder.append("\tifeq L_");
            this.stringBuilder.append(this.labelNumber);
            this.labelNumber++;
            this.stringBuilder.append("\n\tldc 0\n");
            this.stringBuilder.append("\tgoto L_");
            this.stringBuilder.append(this.labelNumber);
            this.labelNumber++;
            this.stringBuilder.append("\nL_");
            this.stringBuilder.append(this.labelNumber - 2);
            this.stringBuilder.append(":\n");
            this.stringBuilder.append("\tldc 1\n");
            this.stringBuilder.append("L_");
            this.stringBuilder.append(this.labelNumber - 1);
            this.stringBuilder.append(":\n");
        } else if (type.equals(new BooleanType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\txor\n");
            this.stringBuilder.append("\tldc 1\n");
            this.stringBuilder.append("\tixor\n");
        }
        else {
            this.assertError();
        }
    }

    public void visit(FloatConstant f) {
        this.stringBuilder.append("\tldc ");
        this.stringBuilder.append(String.format("%.6f", f.getValue()));
        this.stringBuilder.append("\n");
    }

    public void visit(Function f) {
        this.stringBuilder.append(".method public static ");
        String name = f.getName();
        if (name.equals("main")) {
            this.stringBuilder.append("__");
        }
        this.stringBuilder.append(name);
        this.stringBuilder.append(f.getSignature());
        this.stringBuilder.append("\n\t.limit locals ");
        TempFactory tempFactory = f.getTempFactory();
        this.stringBuilder.append(tempFactory.getTempCount());
        // Don't bother computing max stack size for toy compiler
        // Corless suggests setting arbitrary large value
        this.stringBuilder.append("\n\t.limit stack 16\n");
        for (Instruction i : f.getInstructions()) {
            this.stringBuilder.append(";\t\t\t");
            this.stringBuilder.append(i);
            this.stringBuilder.append("\n");
            i.accept(this);
        }
        this.stringBuilder.append(".end method\n");
    }

    public void visit(FunctionCallInstruction f) {
        
    }

    public void visit(FunctionCallOperation f) {

    }

    public void visit(IntegerConstant i) {
        this.stringBuilder.append("\tldc ");
        this.stringBuilder.append(i.getValue());
        this.stringBuilder.append("\n");
    }

    public void visit(Label l) {
        this.stringBuilder.append("L");
        this.stringBuilder.append(l.getNumber());
        this.stringBuilder.append(":\n");
    }

    public void visit(LessThanOperation l) {

    }

    public void visit(MultiplyOperation m) {
        Type type = m.getType();
        int leftNumber = m.getLeftOperand().getNumber();
        int rightNumber = m.getRightOperand().getNumber();
        // Integer
        if (type.equals(new IntegerType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\timul\n");
        // Float
        } else if (type.equals(new FloatType())) {
            this.stringBuilder.append("\tfload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tfload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tfmul\n");
        } else {
            this.assertError();
        }
    }

    public void visit(NewArrayOperand n) {

    }

    public void visit(PrintInstruction p) {
        this.stringBuilder.append("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
        // Integer
        if (p.getType().equals(new IntegerType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/print(I)V\n");
        // Float
        } else if (p.getType().equals(new FloatType())) {
            this.stringBuilder.append("\tfload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/print(F)V\n");
        // Character
        } else if (p.getType().equals(new CharType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/print(C)V\n");
        // String
        } else if (p.getType().equals(new StringType())) {
            this.stringBuilder.append("\taload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append(
                "\n\tinvokevirtual java/io/PrintStream/print(Ljava/lang/String;)V\n");
        // Boolean
        } else if (p.getType().equals(new BooleanType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/print(Z)V\n");
        }
    }

    public void visit(PrintlnInstruction p) {
        this.stringBuilder.append("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
        if (p.getType().equals(new IntegerType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/println(I)V\n");
        } else if (p.getType().equals(new FloatType())) {
            this.stringBuilder.append("\tfload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/println(F)V\n");
        } else if (p.getType().equals(new CharType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/println(C)V\n");
        } else if (p.getType().equals(new StringType())) {
            this.stringBuilder.append("\taload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append(
                "\n\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n");
        } else if (p.getType().equals(new BooleanType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/println(Z)V\n");
        }
    }

    public void visit(ReturnInstruction r) {
        Temp temp = r.getTemp();
        if (temp == null) {
            this.stringBuilder.append("\treturn\n");
        } else {
            // Integer
            Type type = temp.getType();
            int tempNumber = temp.getNumber();
            if (type.equals(new IntegerType())) {
                this.stringBuilder.append("\tiload ");
                this.stringBuilder.append(tempNumber);
                this.stringBuilder.append("\n\tireturn\n");
            // Float
            } else if (type.equals(new FloatType())) {
                this.stringBuilder.append("\tfload ");
                this.stringBuilder.append(tempNumber);
                this.stringBuilder.append("\n\tfreturn\n");
            // Character
            } else if (type.equals(new CharType())) {
                this.stringBuilder.append("\tiload ");
                this.stringBuilder.append(tempNumber);
                this.stringBuilder.append("\n\tireturn\n");
            // String
            } else if (type.equals(new StringType())) {
                this.stringBuilder.append("\taload ");
                this.stringBuilder.append(tempNumber);
                this.stringBuilder.append("\n\tareturn\n");
            // Boolean
            } else if (type.equals(new BooleanType())) {
                this.stringBuilder.append("\tiload ");
                this.stringBuilder.append(tempNumber);
                this.stringBuilder.append("\n\tireturn\n");
            } else {
                this.assertError();
            }
        }
    }

    public void visit(StringConstant s) {
        this.stringBuilder.append("\tldc ");
        this.stringBuilder.append(s.getValue());
        this.stringBuilder.append("\n");
    }

    public void visit(SubtractOperation s) {
        Type type = s.getType();
        int leftNumber = s.getLeftOperand().getNumber();
        int rightNumber = s.getRightOperand().getNumber();
        // Integer
        if (type.equals(new IntegerType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tisub\n");
        // Float
        } else if (type.equals(new FloatType())) {
            this.stringBuilder.append("\tfload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tfload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tfsub\n");
        // Character
        } else if (type.equals(new CharType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(leftNumber);
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(rightNumber);
            this.stringBuilder.append("\n\tisub\n");
            this.stringBuilder.append("\ti2c\n");
        // String
        } else {
            this.assertError();
        }
    }

    public void visit(Temp t) {
        // Integer
        if (t.getType().equals(new IntegerType())) {
            this.stringBuilder.append("\tiload ");
        // Float
        } else if (t.getType().equals(new FloatType())) {
            this.stringBuilder.append("\tfload ");
        // Character
        } else if (t.getType().equals(new CharType())) {
            this.stringBuilder.append("\tiload ");
        // String
        } else if (t.getType().equals(new StringType())) {
            this.stringBuilder.append("\taload ");
        // Boolean
        } else if (t.getType().equals(new BooleanType())) {
            this.stringBuilder.append("\tiload ");
        } else {
            this.assertError();
        }
        this.stringBuilder.append(t.getNumber());
        this.stringBuilder.append("\n");
    }

    public void visit(UnconditionalGotoInstruction u) {
        this.stringBuilder.append("\tgoto L");
        this.stringBuilder.append(u.getLabel().getNumber());
        this.stringBuilder.append("\n");
    }

    public void generateCode() throws IOException {
        this.stringBuilder.append(".source ");
        this.stringBuilder.append(this.program.getName());
        this.stringBuilder.append(".ir\n");
        this.stringBuilder.append(".class public ");
        this.stringBuilder.append(this.program.getName());
        this.stringBuilder.append("\n");
        this.stringBuilder.append(".super java/lang/Object\n\n");
        for (Function f : this.program.getFunctions()) {
            f.accept(this);
            this.stringBuilder.append("\n");
        }
        this.stringBuilder.append(";--------------------------------------------;\n");
        this.stringBuilder.append(";                                            ;\n");
        this.stringBuilder.append(";                Boilerplate                 ;\n");
        this.stringBuilder.append(";                                            ;\n");
        this.stringBuilder.append(";--------------------------------------------;\n\n");
        this.stringBuilder.append(".method public static main([Ljava/lang/String;)V\n");
        this.stringBuilder.append("\t; set limits used by this method\n");
        this.stringBuilder.append("\t.limit locals 1\n");
        this.stringBuilder.append("\t.limit stack 4\n");
        this.stringBuilder.append("\tinvokestatic accept1/__main()V\n");
        this.stringBuilder.append("\treturn\n");
        this.stringBuilder.append(".end method\n\n");
        this.stringBuilder.append("; standard initializer\n");
        this.stringBuilder.append(".method public <init>()V\n");
        this.stringBuilder.append("\taload_0\n");
        this.stringBuilder.append("\tinvokenonvirtual java/lang/Object/<init>()V\n");
        this.stringBuilder.append("\treturn\n");
        this.stringBuilder.append(".end method\n");
        this.writeToFile(this.stringBuilder.toString());
    }

    private void assertError() {
        assert false : "Should never get here";
    }

    private void writeToFile(String str) throws IOException {
        String filename = this.program.getName() + ".j";
        FileWriter fileWriter = new FileWriter(filename);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try {
            bufferedWriter.write(str);
        }
        catch (IOException e) {
            // Bubble up exception to calling code
            throw e;
        }
        finally {
            bufferedWriter.close();
        }
    }
}
