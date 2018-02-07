package ast;

public class MultiplyExpression extends Expression {
    private Expression e1;
    private Expression e2;

    public MultiplyExpression(Expression e1, Expression e2) {
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
