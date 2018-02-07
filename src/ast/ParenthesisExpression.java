package ast;

public class ParenthesisExpression extends Expression {
    private Expression e;

    public ParenthesisExpression(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return this.e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
