package ast;

public class FunctionCall extends Expression {
    private Identifier id;
    private ExpressionList el;

    public FunctionCall(Identifier id, ExpressionList el) {
        this.id = id;
        this.el = el;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public ExpressionList getExpressionList() {
        return this.el;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
