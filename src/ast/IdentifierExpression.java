package ast;

public class IdentifierExpression extends Expression {
    private final Identifier id;

    public IdentifierExpression(Identifier id) {
        this.id = id;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
