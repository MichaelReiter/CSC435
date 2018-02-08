package ast;

import type.Type;

public class IdentifierExpression extends Expression {
    private final Identifier id;

    public IdentifierExpression(Identifier id) {
        this.id = id;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
