package ast;

public abstract class ASTNode {
    public abstract void accept(Visitor v);
}
