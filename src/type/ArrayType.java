package type;

import ast.Visitor;

public class ArrayType extends Type {
    private Type t;
    private int size;

    public ArrayType(Type t, int size) {
        this.t = t;
        this.size = size;
    }

    public String toString() {
        return this.t.toString() + "[" + this.size + "]" ;
    }

    public boolean equals(Object o) {
        if (o instanceof ArrayType) {
            return true;
        } else {
            return false;
        }
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
