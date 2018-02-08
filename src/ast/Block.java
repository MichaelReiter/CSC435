package ast;

public class Block extends ASTNode {
    private final StatementList sl;

    public Block(StatementList sl) {
        this.sl = sl;
    }

    public StatementList getStatementList() {
        return this.sl;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
