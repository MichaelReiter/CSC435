package ast;

public class PrintStatement extends Statement {
    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
