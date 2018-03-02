package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class StringLiteral extends Literal {
    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
