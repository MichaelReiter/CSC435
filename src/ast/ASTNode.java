package ast;

import type.Type;

public abstract class ASTNode {
    private int line;
    private int offset;

    public abstract Type accept(Visitor v);
}
