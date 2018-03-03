package ast;

public class AddExpression extends OperatorExpression {
    public AddExpression(Expression e1, Expression e2) {
        super(e1, e2);
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
