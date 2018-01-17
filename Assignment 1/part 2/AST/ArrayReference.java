package ast;

public class ArrayReference {
    public ArrayReference() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
