package ast;

import type.Type;

public class Block extends ASTNode {
    private final StatementList sl;

    public Block(StatementList sl) {
        this.sl = sl;
    }

    public StatementList getStatementList() {
        return this.sl;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
