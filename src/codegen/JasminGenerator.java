package codegen;

import ir.Program;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JasminGenerator {
    private final Program program;

    public JasminGenerator(Program program) {
        this.program = program;
    }

    public void generateCode() throws IOException {
        this.writeToFile("test\n");
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
