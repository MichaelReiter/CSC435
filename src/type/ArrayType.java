package type;

import ast.Visitor;
import ir.Temp;
import ir.TempVisitor;

public class ArrayType extends Type {
    private final Type t;
    private final int size;

    public ArrayType(Type t, int size) {
        this.t = t;
        this.size = size;
    }

    public Type getType() {
        return this.t;
    }

    public String toString() {
        return this.t.toString() + "[" + this.size + "]" ;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
