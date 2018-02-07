package ast;

public class SubtractExpression extends Expression {
    private Expression e1;
    private Expression e2;

    public SubtractExpression() {}

    public SubtractExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expression getExpression1() {
        return this.e1;
    }

    public Expression getExpression2() {
        return this.e2;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
