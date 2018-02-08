package type;

import ast.Visitor;

public class StringType extends Type {
    public StringType() {}

    public String toString() {
        return "string";
    }

    public boolean equals(Object o) {
        if (o instanceof StringType) {
            return true;
        } else {
            return false;
        }
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
