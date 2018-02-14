package type;

import ast.Visitor;

public class FloatType extends Type {
    public FloatType() {}

    public String toString() {
        return "float";
    }

    public boolean equals(Object o) {
        return o instanceof FloatType;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
