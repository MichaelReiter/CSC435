package type;

import ast.Visitor;

public class StringType extends Type {
    public StringType() {}

    public String toString() {
        return "string";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
