package type;

import ast.Visitor;

public class IntegerType extends Type {
    public IntegerType() {}

    public String toString() {
        return "int";
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
