package ast;

public class LessThanExpression extends Expression {
    private final Expression e1;
    private final Expression e2;

    public LessThanExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expression getLeftExpression() {
        return this.e1;
    }

    public Expression getRightExpression() {
        return this.e2;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
