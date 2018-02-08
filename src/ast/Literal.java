package ast;

import type.Type;

public abstract class Literal extends Expression {
    public abstract Type accept(Visitor v);
}
