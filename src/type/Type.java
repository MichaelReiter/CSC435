package type;

import ast.Visitor;

public abstract class Type {
    private int line;
    private int offset;

    public abstract String toString();
    public abstract boolean equals(Object o);
    public abstract Type accept(Visitor v);
    
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
