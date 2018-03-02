package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class PrintlnStatement extends Statement {
    private final Expression e;

    public PrintlnStatement(Expression e) {
        this.e = e;
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
