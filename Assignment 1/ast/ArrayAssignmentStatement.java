package ast;

public class ArrayAssignmentStatement extends Statement {
    ArrayReference a;
    Expression e;

    public ArrayAssignmentStatement(ArrayReference a, Expression e) {
        this.a = a;
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
