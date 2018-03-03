package ast;

public class PrintlnStatement extends Statement {
    private final Expression expression;

    public PrintlnStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
