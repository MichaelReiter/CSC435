package ast;

public abstract class ASTNode implements Visitable {
    private int line;
    private int offset;

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
