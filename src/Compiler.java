import ast.Program;
import ir.IRVisitor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import semantic.SemanticException;
import semantic.TypeCheckVisitor;

public class Compiler {
    public void compile(String filename) throws FileNotFoundException,
                                                IOException,
                                                RecognitionException {
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(filename));
        ulLexer lexer = new ulLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ulParser parser = new ulParser(tokens);
        ast.Program program = parser.program();
        TypeCheckVisitor typeCheckVisitor = new TypeCheckVisitor();
        program.accept(typeCheckVisitor);
        int slashIndex = filename.lastIndexOf('/');
        int dotIndex = filename.lastIndexOf('.');
        String programName = filename.substring(slashIndex + 1, dotIndex);
        IRVisitor irVisitor = new IRVisitor(programName);
        program.accept(irVisitor);
        ir.Program irProgram = irVisitor.getProgram();
        System.out.println(irProgram);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: Compiler <filename>");
            return;
        }
        try {
            Compiler compiler = new Compiler();
            compiler.compile(args[0]);
        }
        catch (RecognitionException e) {
            // A lexical or parsing error occured.
            // ANTLR will have already printed information on the console due
            // to code added to the grammar so there is nothing to do here.
        }
        catch (SemanticException e) {
            System.out.println(e);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Could not compile "  + args[0] + ". File not found.");
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
