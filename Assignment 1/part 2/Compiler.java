/*
 Compiler.java
 A starting place for the unamed language compiler for CSC 435
 */

import org.antlr.runtime.*;
import java.io.*;

public class Compiler {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input;

        if (args.length == 0) {
            System.out.println("Usage: Compiler filename.ul");
            return;
        } else {
            input = new ANTLRInputStream(new FileInputStream(args[0]));
        }

        // The name of the grammar here is "ulNoActions" so ANTLR generates ulNoActionsLexer and ulNoActionsParser
        ulNoActionsLexer lexer = new ulNoActionsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ulNoActionsParser parser = new ulNoActionsParser(tokens);

        try {
            Program p = parser.program();
            Visitor v = new PrintVisitor();
            p.accept(v);
        }
        catch (RecognitionException e ) {
            // A lexical or parsing error occured.
            // ANTLR will have already printed information on the console due
            // to code added to the grammar so there is nothing to do here.
        }
        catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}