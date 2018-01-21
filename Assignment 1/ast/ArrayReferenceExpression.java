package ast;

public class ArrayReferenceExpression extends Atom {
    ArrayReference a;

    public ArrayReferenceExpression(ArrayReference a) {
        this.a = a;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
