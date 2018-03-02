package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public abstract class Literal extends Expression {
    public abstract Type accept(Visitor v);
    public abstract Temp accept(TempVisitor v);
}
