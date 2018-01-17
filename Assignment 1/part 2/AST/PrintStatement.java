package ast;

public class PrintStatement extends Statement {
    public PrintStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
