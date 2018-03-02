package type;

import ast.Visitor;
import ir.Temp;
import ir.TempVisitor;

public class FloatType extends Type {
    public FloatType() {}

    public String toString() {
        return "float";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
