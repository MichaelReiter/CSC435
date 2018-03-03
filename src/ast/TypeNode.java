package ast;

import type.Type;

public class TypeNode extends ASTNode {
    private final Type t;
    private final int size;

    public TypeNode(Type t) {
        this.t = t;
        this.size = 0;
    }

    public TypeNode(Type t, int size) {
        this.t = t;
        this.size = size;
    }

    public Type getType() {
        return this.t;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
