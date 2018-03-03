package ast;

public class ParenthesisExpression extends Expression {
    private final Expression expression;

    public ParenthesisExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
