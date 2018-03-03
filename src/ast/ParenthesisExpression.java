package ast;

public class ParenthesisExpression extends Expression {
    private final Expression e;

    public ParenthesisExpression(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return this.e;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
