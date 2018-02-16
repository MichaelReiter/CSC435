package type;

import ast.Visitor;

public class FloatType extends Type {
    public FloatType() {}

    public String toString() {
        return "float";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
