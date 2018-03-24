package codegen;

import ir.Function;
import ir.Instruction;
import ir.Program;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class JasminGenerator {
    private final Program program;

    public JasminGenerator(Program program) {
        this.program = program;
    }

    private String getJasmin(Function function) {
        StringBuilder sb = new StringBuilder();
        sb.append(".method public static ");
        String name = function.getName();
        if (name.equals("main")) {
            sb.append("__");
        }
        sb.append(name);
        sb.append(function.getSignature());
        sb.append("\n\t.limit locals ");
        sb.append(function.getTempFactory().getTempCount());
        // YOLO
        sb.append("\n\t.limit stack 1000");
        for (Instruction i : function.getInstructions()) {
            sb.append(this.getJasmin(i));
        }
        sb.append("\n.end method\n");
        return sb.toString();
    }

    private String getJasmin(Instruction instruction) {
        return "";
    }

    public void generateCode() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(".source ");
        sb.append(this.program.getName());
        sb.append(".ir\n");
        sb.append(".class public ");
        sb.append(this.program.getName());
        sb.append("\n");
        sb.append(".super java/lang/Object\n\n");
        for (Function f : this.program.getFunctions()) {
            sb.append(this.getJasmin(f));
            sb.append("\n");
        }
        sb.append(";--------------------------------------------;\n");
        sb.append(";                                            ;\n");
        sb.append(";                Boilerplate                 ;\n");
        sb.append(";                                            ;\n");
        sb.append(";--------------------------------------------;\n\n");
        sb.append(".method public static main([Ljava/lang/String;)V\n");
        sb.append("\t; set limits used by this method\n");
        sb.append("\t.limit locals 1\n");
        sb.append("\t.limit stack 4\n");
        sb.append("\tinvokestatic accept1/__main()V\n");
        sb.append("\treturn\n");
        sb.append(".end method\n\n");
        sb.append("; standard initializer\n");
        sb.append(".method public <init>()V\n");
        sb.append("\taload_0\n");
        sb.append("\tinvokenonvirtual java/lang/Object/<init>()V\n");
        sb.append("\treturn\n");
        sb.append(".end method\n");
        this.writeToFile(sb.toString());
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
