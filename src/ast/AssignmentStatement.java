package ast;

import type.Type;

public class AssignmentStatement extends Statement {
    private final Identifier id;
    private final Expression e;

    public AssignmentStatement(Identifier id, Expression e) {
        this.id = id;
        this.e = e;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
