package ast;

public class IfStatement extends Statement {
    private final Expression expression;
    private final Block block;
    
    public IfStatement(Expression expression, Block block) {
        this.expression = expression;
        this.block = block;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public Block getBlock() {
        return this.block;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
