package ast;

public class ArrayReference extends Expression {
    Identifier id;
    Expression e;

    public ArrayReference(Identifier id, Expression e) {
        this.id = id;
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
