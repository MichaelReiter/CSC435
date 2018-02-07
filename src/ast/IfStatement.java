package ast;

public class IfStatement extends Statement {
    private Expression e;
    private Block b;
    
    public IfStatement(Expression e, Block b) {
        this.e = e;
        this.b = b;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Block getBlock() {
        return this.b;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
