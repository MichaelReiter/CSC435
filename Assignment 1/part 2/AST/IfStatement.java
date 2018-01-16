package AST;

public class IfStatement extends Statement {
    public IfStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
