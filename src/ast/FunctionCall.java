package ast;

public class FunctionCall extends Expression {
    private final Identifier identifier;
    private final ExpressionList expressionList;

    public FunctionCall(Identifier identifier, ExpressionList expressionList) {
        this.identifier = identifier;
        this.expressionList = expressionList;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public ExpressionList getExpressionList() {
        return this.expressionList;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
