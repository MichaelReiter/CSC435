package ast;

public class ReturnStatement extends Statement {
    private Expression e;

    public ReturnStatement(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return this.e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
