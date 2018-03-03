package type;

import ast.Visitor;

public class FloatType extends Type {
    public FloatType() {}

    public String toString() {
        return "float";
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
