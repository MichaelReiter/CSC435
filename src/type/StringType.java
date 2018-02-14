package type;

import ast.Visitor;

public class StringType extends Type {
    public StringType() {}

    public String toString() {
        return "string";
    }

    public boolean equals(Object o) {
        return o instanceof StringType;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
