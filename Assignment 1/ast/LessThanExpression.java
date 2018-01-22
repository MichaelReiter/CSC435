package ast;

public class LessThanExpression extends Expression {
    Expression e1;
    Expression e2;

    public LessThanExpression() {}

    public LessThanExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
