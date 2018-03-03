package type;

import ast.Visitor;

public class VoidType extends Type {
    public VoidType() {}

    public String toString() {
        return "void";
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
