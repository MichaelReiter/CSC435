package ast;

import type.Type;

public class EmptyStatement extends Statement {
    public EmptyStatement() {}

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
