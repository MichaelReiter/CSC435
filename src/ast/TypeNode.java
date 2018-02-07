package ast;

import type.Type;

public class TypeNode extends ASTNode {
    private Type t;
    private int size;

    public TypeNode(Type t) {
        this.t = t;
    }

    public Type getType() {
        return this.t;
    }

    public int getSize() {
        return this.size;
    }

    public TypeNode(Type t, int size) {
        this.t = t;
        this.size = size;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
