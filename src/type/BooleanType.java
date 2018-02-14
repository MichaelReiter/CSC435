package type;

import ast.Visitor;

public class BooleanType extends Type {
    public BooleanType() {}

    public String toString() {
        return "boolean";
    }

    public boolean equals(Object o) {
        return o instanceof BooleanType;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
