package type;

import ast.Visitor;
import ir.Temp;
import ir.TempVisitor;

public class StringType extends Type {
    public StringType() {}

    public String toString() {
        return "string";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
