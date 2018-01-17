package ast;

public class ReturnStatement extends Statement {
    public ReturnStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
