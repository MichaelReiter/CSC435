package type;

import ast.Visitor;

public class CharType extends Type {
    public CharType() {}

    public String toString() {
        return "char";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
