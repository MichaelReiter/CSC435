package type;

import ast.Visitor;

public class CharType extends Type {
    public CharType() {}

    public String toString() {
        return "char";
    }

    public boolean equals(Object o) {
        return o instanceof CharType;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
