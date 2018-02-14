package type;

import ast.Visitor;

public class VoidType extends Type {
    public VoidType() {}

    public String toString() {
        return "void";
    }

    public boolean equals(Object o) {
        return o instanceof VoidType;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
