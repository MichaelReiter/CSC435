package ast;

public class IfElseStatement extends Statement {
    private final Expression expression;
    private final Block ifBlock;
    private final Block elseBlock;
    
    public IfElseStatement(Expression expression, Block ifBlock, Block elseBlock) {
        this.expression = expression;
        this.ifBlock = ifBlock;
        this.elseBlock = elseBlock;
    }

    public Expression getExpression() {
        return this.expression;
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
