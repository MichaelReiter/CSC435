package ast;

import type.Type;

public class Identifier extends Expression {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
