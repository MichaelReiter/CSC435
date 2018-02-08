package ast;

public class ExpressionStatement extends Statement {
    private final Expression e;

    public ExpressionStatement(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return this.e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
