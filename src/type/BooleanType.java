package type;

import ast.Visitor;
import ir.Temp;
import ir.TempVisitor;

public class BooleanType extends Type {
    public BooleanType() {}

    public String toString() {
        return "boolean";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
