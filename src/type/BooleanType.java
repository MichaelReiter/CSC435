package type;

import ast.Visitor;

public class BooleanType extends Type {
    public BooleanType() {}

    public String toString() {
        return "boolean";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
