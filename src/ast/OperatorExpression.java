package ast;

public abstract class OperatorExpression extends Expression {
    private final Expression expression1;
    private final Expression expression2;

    public OperatorExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    public Expression getLeftExpression() {
        return this.expression1;
    }

    public Expression getRightExpression() {
        return this.expression2;
    }
}
