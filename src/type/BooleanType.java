package type;

import ast.Visitor;

public class BooleanType extends Type {
    public BooleanType() {}

    public String toString() {
        return "boolean";
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
