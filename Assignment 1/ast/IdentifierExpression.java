package ast;

public class IdentifierExpression extends Expression {
    Identifier id;

    public IdentifierExpression(Identifier id) {
        this.id = id;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
