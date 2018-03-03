package ast;

public class ArrayReference extends Expression {
    private final Identifier identifier;
    private final Expression expression;

    public ArrayReference(Identifier identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
