package type;

import ast.Visitor;

public class CharType extends Type {
    public CharType() {}

    public String toString() {
        return "char";
    }

    public boolean equals(Object o) {
        if (o instanceof CharType) {
            return true;
        } else {
            return false;
        }
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
