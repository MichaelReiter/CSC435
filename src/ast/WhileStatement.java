package ast;

public class WhileStatement extends Statement {
    private final Expression e;
    private final Block b;

    public WhileStatement(Expression e, Block b) {
        this.e = e;
        this.b = b;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Block getBlock() {
        return this.b;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
