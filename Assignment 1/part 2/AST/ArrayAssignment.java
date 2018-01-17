package ast;

public class ArrayAssignment {
    public ArrayAssignment() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
