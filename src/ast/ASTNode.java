package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public abstract class ASTNode {
    private int line;
    private int offset;
    
    public abstract Type accept(Visitor v);
    public abstract Temp accept(TempVisitor v);

    public final int getLine() {
        return this.line;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final void setLine(int line) {
        this.line = line;
    }

    public final void setOffset(int offset) {
        this.offset = offset;
    }
}
