package ast;

public class IdentifierExpression extends Expression {
    private final Identifier identifier;

    public IdentifierExpression(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
