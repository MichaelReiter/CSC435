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

public class JasminVisitor implements CodeGenVisitor {
    private final Program program;
    private final StringBuilder stringBuilder;

    public JasminVisitor(Program program) {
        this.program = program;
        this.stringBuilder = new StringBuilder();
    }

    public void visit(AddOperation a) {

    }

    public void visit(ArrayInitialization a) {
        
    }

    public void visitArrayAssignment(Temp left) {
        this.stringBuilder.append("\taload ");
        this.stringBuilder.append(((ArrayReference)left).getIdentifier().getNumber());
        // Integer
        if (left.getType().equals(new IntegerType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            this.stringBuilder.append("\n\tiastore\n");
        // Float
        } else if (left.getType().equals(new FloatType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            this.stringBuilder.append("\n\tfastore\n");
        // Character
        } else if (left.getType().equals(new CharType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            this.stringBuilder.append("\n\tcastore\n");
        // String
        } else if (left.getType().equals(new StringType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            this.stringBuilder.append("\n\taastore\n");
        // Boolean
        } else if (left.getType().equals(new BooleanType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            this.stringBuilder.append("\n\tbastore\n");
        }
    }

    public void visitArrayDeclaration(Temp left) {
        this.stringBuilder.append("\tldc ");
        this.stringBuilder.append(((ArrayType)left.getType()).getSize());
        // Integer
        if (((ArrayType)left.getType()).getType().equals(new IntegerType())) {
            this.stringBuilder.append("\n\tnewarray int\n");
        // Float
        } else if (((ArrayType)left.getType()).getType().equals(new FloatType())) {
            this.stringBuilder.append("\n\tnewarray float\n");
        // Character
        } else if (((ArrayType)left.getType()).getType().equals(new CharType())) {
            this.stringBuilder.append("\n\tnewarray char\n");
        // String
        } else if (((ArrayType)left.getType()).getType().equals(new StringType())) {
            this.stringBuilder.append("\n\tanewarray java/lang/String\n");
        // Boolean
        } else if (((ArrayType)left.getType()).getType().equals(new BooleanType())) {
            this.stringBuilder.append("\n\tnewarray boolean\n");
        }
        this.stringBuilder.append("\tastore ");
        this.stringBuilder.append(left.getNumber());
        this.stringBuilder.append("\n");
    }

    public void visitVariableAssignment(Temp left, Operand right) {
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
            System.out.println("this should not happen");
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
            this.visitArrayAssignment(left);
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
        this.stringBuilder.append("\n\t.limit stack 1000\n");
        // Initialize variables
        // for (Temp t : tempFactory.getTemps()) {
        //     if (t.getType().equals(new IntegerType())) {
        //         this.stringBuilder.append("\tldc 0\n");
        //         this.stringBuilder.append("\tistore ");
        //     } else if (t.getType().equals(new FloatType())) {
        //         this.stringBuilder.append("\tldc 0.0\n");
        //         this.stringBuilder.append("\tfstore ");
        //     } else if (t.getType().equals(new CharType())) {
        //         this.stringBuilder.append("\tldc 0\n");
        //         this.stringBuilder.append("\tistore ");
        //     } else if (t.getType().equals(new StringType())) {
        //         this.stringBuilder.append("\taconst_null\n");
        //         this.stringBuilder.append("\tastore ");
        //     } else if (t.getType().equals(new BooleanType())) {
        //         this.stringBuilder.append("\tldc 0\n");
        //         this.stringBuilder.append("\tistore ");
        //     }
        //     this.stringBuilder.append(t.getNumber());
        //     this.stringBuilder.append("\n");
        // }
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
        
    }

    public void visit(LessThanOperation l) {

    }

    public void visit(MultiplyOperation m) {

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
        this.stringBuilder.append("\treturn\n");
    }

    public void visit(StringConstant s) {
        this.stringBuilder.append("\tldc ");
        this.stringBuilder.append(s.getValue());
        this.stringBuilder.append("\n");
    }

    public void visit(SubtractOperation s) {

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
            this.stringBuilder.append("THIS SHOULD NOT HAPPEN");
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
