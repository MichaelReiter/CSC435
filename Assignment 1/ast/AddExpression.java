package ast;

public class AddExpression extends LessThanExpression {
    Expression e1;
    Expression e2;

    public AddExpression() {}

    public AddExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
