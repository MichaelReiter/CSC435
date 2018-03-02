package ast;

import ir.Temp;
import ir.TempVisitor;
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

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
