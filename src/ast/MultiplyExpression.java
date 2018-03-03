package ast;

public class MultiplyExpression extends OperatorExpression {
    public MultiplyExpression(Expression e1, Expression e2) {
        super(e1, e2);
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
