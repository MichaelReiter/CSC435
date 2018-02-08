package ast;

import type.Type;

public class CharacterLiteral extends Literal {
    private final char value;

    public CharacterLiteral(char value) {
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
