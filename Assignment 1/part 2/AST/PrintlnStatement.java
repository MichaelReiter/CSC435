package AST;

public class PrintlnStatement extends Statement {
    public PrintlnStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
