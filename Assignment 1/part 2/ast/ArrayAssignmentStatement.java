package ast;

public class ArrayAssignmentStatement extends Statement {
    public ArrayAssignmentStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
