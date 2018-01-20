/*
 Compiler.java
 A starting place for the unamed language compiler for CSC 435
 */

import org.antlr.runtime.*;
import java.io.*;
import ast.*;
import type.*;

public class Compiler {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input;

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
