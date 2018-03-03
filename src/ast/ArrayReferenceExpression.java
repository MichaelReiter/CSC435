package ast;

public class ArrayReferenceExpression extends Expression {
    private final ArrayReference a;

    public ArrayReferenceExpression(ArrayReference a) {
        this.a = a;
    }

    public ArrayReference getArrayReference() {
        return this.a;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
