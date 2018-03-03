package ast;

public class EqualityExpression extends OperatorExpression {
    public EqualityExpression(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
