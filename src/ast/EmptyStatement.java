package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class EmptyStatement extends Statement {
    public EmptyStatement() {}

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
