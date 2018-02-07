package ast;

public class WhileStatement extends Statement {
    Expression e;
    Block b;

    public WhileStatement(Expression e, Block b) {
        this.e = e;
        this.b = b;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
