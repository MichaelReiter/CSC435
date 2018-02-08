package ast;

public class PrintlnStatement extends Statement {
    private final Expression e;

    public PrintlnStatement(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return this.e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
