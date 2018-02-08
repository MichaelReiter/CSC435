package ast;

import type.Type;

public class ParenthesisExpression extends Expression {
    private final Expression e;

    public ParenthesisExpression(Expression e) {
        this.e = e;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
