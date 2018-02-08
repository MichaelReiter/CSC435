package ast;

import type.Type;

public class IntegerLiteral extends Literal {
    private final int value;

    public IntegerLiteral(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
