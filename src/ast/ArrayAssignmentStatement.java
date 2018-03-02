package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class ArrayAssignmentStatement extends Statement {
    private final ArrayReference a;
    private final Expression e;

    public ArrayAssignmentStatement(ArrayReference a, Expression e) {
        this.a = a;
        this.e = e;
    }

    public ArrayReference getArrayReference() {
        return this.a;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
