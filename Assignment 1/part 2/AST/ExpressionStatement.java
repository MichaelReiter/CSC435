package AST;

public class ExpressionStatement extends Statement {
    public ExpressionStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
