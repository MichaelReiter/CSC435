package ast;

public class ArrayReference extends Expression {
    public ArrayReference() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
