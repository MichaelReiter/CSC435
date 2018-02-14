package type;

import ast.Visitor;

public class ArrayType extends Type {
    private Type t;
    private int size;

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

    public boolean equals(Object o) {
        return o instanceof ArrayType;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
