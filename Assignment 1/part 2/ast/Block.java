package ast;

public class Block extends ASTNode {
    public Block() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
