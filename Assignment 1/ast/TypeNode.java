package ast;

import type.*;

public class TypeNode extends ASTNode {
    Type t;

    public TypeNode(Type t) {
        this.t = t;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
