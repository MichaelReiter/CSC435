package ast;

public class ArrayReferenceExpression extends Expression {
    ArrayReference a;

    public ArrayReferenceExpression(ArrayReference a) {
        this.a = a;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
