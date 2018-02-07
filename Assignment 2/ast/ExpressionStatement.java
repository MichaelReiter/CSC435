package ast;

public class ExpressionStatement extends Statement {
    Expression e;

    public ExpressionStatement(Expression e) {
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
