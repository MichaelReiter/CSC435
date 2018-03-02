package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class FunctionCall extends Expression {
    private final Identifier id;
    private final ExpressionList el;

    public FunctionCall(Identifier id, ExpressionList el) {
        this.id = id;
        this.el = el;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public ExpressionList getExpressionList() {
        return this.el;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
