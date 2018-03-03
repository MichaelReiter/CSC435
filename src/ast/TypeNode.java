package ast;

import type.Type;

public class TypeNode extends ASTNode {
    private final Type type;
    private final int size;

    public TypeNode(Type type) {
        this.type = type;
        this.size = 0;
    }

    public TypeNode(Type type, int size) {
        this.type = type;
        this.size = size;
    }

    public Type getType() {
        return this.type;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
