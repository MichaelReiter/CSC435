package type;

import ast.Visitor;

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

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
