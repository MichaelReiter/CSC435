package ast;

public class IfElseStatement extends Statement {
    private final Expression e;
    private final Block ifBlock;
    private final Block elseBlock;
    
    public IfElseStatement(Expression e, Block ifBlock, Block elseBlock) {
        this.e = e;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public Expression getExpression() {
        return this.e;
    }

    public Block getIfBlock() {
        return this.ifBlock;
    }

    public Block getElseBlock() {
        return this.elseBlock;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
