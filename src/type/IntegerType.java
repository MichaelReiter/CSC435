package type;

import ast.Visitor;

public class IntegerType extends Type {
    public IntegerType() {}

    public String toString() {
        return "int";
    }

    public boolean equals(Object o) {
        return o instanceof IntegerType;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
