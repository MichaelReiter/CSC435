package ast;

public class Block extends ASTNode {
    StatementList sl;

    public Block(StatementList sl) {
        this.sl = sl;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
