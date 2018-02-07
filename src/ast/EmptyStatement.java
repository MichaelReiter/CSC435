package ast;

public class EmptyStatement extends Statement {
    public EmptyStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
