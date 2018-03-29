package codegen;

import ir.ArrayReference;
import ir.AssignmentInstruction;
import ir.ConditionalGotoInstruction;
import ir.Function;
import ir.FunctionCallInstruction;
import ir.Instruction;
import ir.Label;
import ir.PrintInstruction;
import ir.PrintlnInstruction;
import ir.Program;
import ir.ReturnInstruction;
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
            this.stringBuilder.append("\n\tfload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            this.stringBuilder.append("\n\tfastore\n");
        // Character
        } else if (left.getType().equals(new CharType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            this.stringBuilder.append("\n\tiastore\n");
        // String
        } else if (left.getType().equals(new StringType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            // this.stringBuilder.append("\n\tuastore\n");
        // Boolean
        } else if (left.getType().equals(new BooleanType())) {
            this.stringBuilder.append("\n\tiload ");
            this.stringBuilder.append(((ArrayReference)left).getIndex().getNumber());

            // this.stringBuilder.append("\n\tiastore\n");
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

    public void visitVariableAssignment(Temp left) {
        // Integer
        if (left.getType().equals(new IntegerType())) {
                
            this.stringBuilder.append("\n\tistore ");
        // Float
        } else if (left.getType().equals(new FloatType())) {
            
            this.stringBuilder.append("\n\tfstore ");
        // Character
        } else if (left.getType().equals(new CharType())) {
            
            this.stringBuilder.append("\n\tistore ");
        // String
        } else if (left.getType().equals(new StringType())) {
            
            this.stringBuilder.append("\n\tastore ");
        // Boolean
        } else if (left.getType().equals(new BooleanType())) {
            
            this.stringBuilder.append("\n\tistore ");
        } else {

            this.stringBuilder.append(left.getType());
        }
        this.stringBuilder.append(left.getNumber());
        this.stringBuilder.append("\n");
    }

    public void visit(AssignmentInstruction a) {
        Temp left = a.getLeftOperand();
        // Temp right = a.getRightOperand();
        if (left.getType() instanceof ArrayType) {
            this.visitArrayDeclaration(left);
        } else if (left instanceof ArrayReference) {
            this.visitArrayAssignment(left);
        } else {
            this.visitVariableAssignment(left);
        }
    }

    public void visit(ConditionalGotoInstruction c) {
        this.stringBuilder.append("\tiload ");
        this.stringBuilder.append(c.getCondition().getNumber());
        this.stringBuilder.append("\n\tifne L");
        this.stringBuilder.append(c.getLabel().getNumber());
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

    public void visit(Label l) {
        
    }

    public void visit(PrintInstruction p) {
        this.stringBuilder.append("\tgetstatic java/lang/System/out Ljava/io/PrintStream;\n");
        if (p.getType().equals(new IntegerType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/print(I)V\n");
        } else if (p.getType().equals(new FloatType())) {
            this.stringBuilder.append("\tfload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/print(F)V\n");
        } else if (p.getType().equals(new CharType())) {
            this.stringBuilder.append("\tiload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append("\n\tinvokevirtual java/io/PrintStream/print(C)V\n");
        } else if (p.getType().equals(new StringType())) {
            this.stringBuilder.append("\taload ");
            this.stringBuilder.append(p.getTemp().getNumber());
            this.stringBuilder.append(
                "\n\tinvokevirtual java/io/PrintStream/print(Ljava/lang/String;)V\n");
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

    public void visit(Temp t) {

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
