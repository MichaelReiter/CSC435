package type;

import ast.Visitor;

public class CharType extends Type {
    public CharType() {}

    public String toString() {
        return "char";
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
