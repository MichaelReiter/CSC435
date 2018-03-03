package ast;

public class Block extends ASTNode {
    private final StatementList statementList;

    public Block(StatementList statementList) {
        this.statementList = statementList;
    }

    public StatementList getStatementList() {
        return this.statementList;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
