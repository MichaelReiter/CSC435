package ast;

public class AddExpression extends Expression {
    private final Expression e1;
    private final Expression e2;

    public AddExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expression getLeftExpression() {
        return this.e1;
    }

    public Expression getRightExpression() {
        return this.e2;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
