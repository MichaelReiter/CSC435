package type;

import ast.Visitor;

public class IntegerType extends Type {
    public IntegerType() {}

    public String toString() {
        return "int";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
