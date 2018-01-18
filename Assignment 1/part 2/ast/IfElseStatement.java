package ast;

public class IfElseStatement extends Statement {
    public IfElseStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
