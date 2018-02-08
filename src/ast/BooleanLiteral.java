package ast;

import type.Type;

public class BooleanLiteral extends Literal {
    private final boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
