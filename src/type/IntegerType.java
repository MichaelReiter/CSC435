package type;

import ast.Visitor;
import ir.Temp;
import ir.TempVisitor;

public class IntegerType extends Type {
    public IntegerType() {}

    public String toString() {
        return "int";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
