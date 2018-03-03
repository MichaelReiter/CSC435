package ast;

public class SubtractExpression extends OperatorExpression {
    public SubtractExpression(Expression e1, Expression e2) {
        super(e1, e2);
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
