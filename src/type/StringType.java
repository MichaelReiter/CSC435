package type;

import ast.Visitor;

public class StringType extends Type {
    public StringType() {}

    public String toString() {
        return "string";
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
