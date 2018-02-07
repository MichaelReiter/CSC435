package ast;

public class ArrayReference extends Expression {
    private Identifier id;
    private Expression e;

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

    public void accept(Visitor v) {
        v.visit(this);
    }
}
