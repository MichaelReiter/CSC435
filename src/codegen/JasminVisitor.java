package codegen;

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
import ir.UnconditionalGotoInstruction;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class JasminVisitor implements CodeGenVisitor {
    private final Program program;
    private final StringBuilder stringBuilder;

    public JasminVisitor(Program program) {
        this.program = program;
        this.stringBuilder = new StringBuilder();
    }

    public void visit(AssignmentInstruction a) {
        
    }

    public void visit(ConditionalGotoInstruction c) {
        
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
        this.stringBuilder.append(f.getTempFactory().getTempCount());
        // Don't bother computing max stack size for toy compiler
        // Corless suggests setting arbitrary large value
        this.stringBuilder.append("\n\t.limit stack 1000");
        for (Instruction i : f.getInstructions()) {
            // i.accept(this);
        }
        this.stringBuilder.append("\n.end method\n");
    }

    public void visit(FunctionCallInstruction f) {
        
    }

    public void visit(Label l) {
        
    }

    public void visit(PrintInstruction p) {
        
    }

    public void visit(PrintlnInstruction p) {
        
    }

    public void visit(ReturnInstruction r) {
        
    }

    public void visit(UnconditionalGotoInstruction u) {
        
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
            // f.accept(this);
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
