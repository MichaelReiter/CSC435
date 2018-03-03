package ast;

public class ArrayReference extends Expression {
    private final Identifier id;
    private final Expression e;

    public ArrayReference(Identifier id, Expression e) {
        this.id = id;
        this.e = e;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public Expression getExpression() {
        return this.e;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
