package ast;

public class FunctionCall extends Expression {
    private final Identifier id;
    private final ExpressionList expressionList;

    public FunctionCall(Identifier id, ExpressionList expressionList) {
        this.id = id;
        this.expressionList = expressionList;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public ExpressionList getExpressionList() {
        return this.expressionList;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
