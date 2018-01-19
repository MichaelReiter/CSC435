package ast;

public class WhileStatement extends Statement {
    public WhileStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
