package ast;

public class Block {
    public Block() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
