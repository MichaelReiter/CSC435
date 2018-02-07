package ast;

public class IdentifierExpression extends Expression {
    private Identifier id;

    public IdentifierExpression(Identifier id) {
        this.id = id;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
