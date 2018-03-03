package ast;

public class IfElseStatement extends Statement {
    private final Expression e;
    private final Block b1;
    private final Block b2;
    
    public IfElseStatement(Expression e, Block b1, Block b2) {
        this.e = e;
        this.b1 = b1;
        this.b2 = b2;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Block getIfBlock() {
        return this.b1;
    }

    public Block getElseBlock() {
        return this.b2;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
