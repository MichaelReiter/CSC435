package ast;

import type.*;

public class TypeNode extends ASTNode {
    Type t;
    int size;

    public TypeNode(Type t) {
        this.t = t;
    }

    public TypeNode(Type t, int size) {
        this.t = t;
        this.size = size;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
