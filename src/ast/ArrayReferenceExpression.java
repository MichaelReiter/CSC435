package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class ArrayReferenceExpression extends Expression {
    private final ArrayReference a;

    public ArrayReferenceExpression(ArrayReference a) {
        this.a = a;
    }

    public ArrayReference getArrayReference() {
        return this.a;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
