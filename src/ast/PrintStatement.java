package ast;

public class PrintStatement extends Statement {
    private Expression e;

    public PrintStatement(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return this.e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
