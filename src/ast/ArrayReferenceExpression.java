package ast;

public class ArrayReferenceExpression extends Expression {
    private ArrayReference a;

    public ArrayReferenceExpression(ArrayReference a) {
        this.a = a;
    }

    public ArrayReference getArrayReference() {
        return this.a;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
