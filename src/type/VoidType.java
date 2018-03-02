package type;

import ast.Visitor;
import ir.Temp;
import ir.TempVisitor;

public class VoidType extends Type {
    public VoidType() {}

    public String toString() {
        return "void";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
    
    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
