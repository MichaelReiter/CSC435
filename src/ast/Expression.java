package ast;

import type.Type;

public abstract class Expression extends ASTNode {
    public abstract Type accept(Visitor v);
}
