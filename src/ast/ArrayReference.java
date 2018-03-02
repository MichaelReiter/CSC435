package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class ArrayReference extends Expression {
    private final Identifier id;
    private final Expression e;

    public ArrayReference(Identifier id, Expression e) {
        this.id = id;
        this.e = e;
    }

    public Identifier getIdentifier() {
        return this.id;
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
