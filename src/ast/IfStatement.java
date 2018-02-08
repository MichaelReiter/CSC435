package ast;

import type.Type;

public class IfStatement extends Statement {
    private final Expression e;
    private final Block b;
    
    public IfStatement(Expression e, Block b) {
        this.e = e;
        this.b = b;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Block getBlock() {
        return this.b;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
