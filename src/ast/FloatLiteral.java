package ast;

import type.Type;

public class FloatLiteral extends Literal {
    private final float value;

    public FloatLiteral(float value) {
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
