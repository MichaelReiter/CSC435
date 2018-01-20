package type;

import ast.Visitor;

public class ArrayType extends Type {
    public ArrayType() {}

    public String toString() {
        return "array";
    }

    public boolean equals(Object o) {
        if (o instanceof ArrayType) {
            return true;
        } else {
            return false;
        }
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
