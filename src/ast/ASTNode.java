package ast;

public abstract class ASTNode {
    private int line;
    private int offset;

    public abstract void accept(Visitor v);
}
