package ast;

import type.Type;

public class ExpressionStatement extends Statement {
    private final Expression e;

    public ExpressionStatement(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
