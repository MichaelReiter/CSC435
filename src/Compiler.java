import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import ast.Program;
import ast.Visitor;
import semantic.SemanticException;
import semantic.TypeCheckVisitor;

public class Compiler {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input;

        try {
            if (args.length == 0) {
                System.out.println("Usage: Compiler filename.ul");
                return;
            } else {
                input = new ANTLRInputStream(new FileInputStream(args[0]));
            }

            // The name of the grammar here is "ul" so ANTLR generates ulLexer and ulParser
            ulLexer lexer = new ulLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ulParser parser = new ulParser(tokens);

            try {
                Program p = parser.program();
                Visitor v = new TypeCheckVisitor();
                p.accept(v);
            }
            catch (RecognitionException e) {
                // A lexical or parsing error occured.
                // ANTLR will have already printed information on the console due
                // to code added to the grammar so there is nothing to do here.
            }
            catch (SemanticException e) {
                // A type checking or semantic analyis error occured.
                // The TypeCheckVisitor will have already printed information on the console due
                // to code added to the grammar so there is nothing to do here.
            }
            catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Could not compile "  + args[0] + ". File not found.");
        }
    }
}
