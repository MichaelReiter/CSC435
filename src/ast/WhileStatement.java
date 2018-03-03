package ast;

public class WhileStatement extends Statement {
    private final Expression e;
    private final Block block;

    public WhileStatement(Expression e, Block block) {
        this.e = e;
        this.block = block;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Block getBlock() {
        return this.block;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
