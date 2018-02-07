package ast;

public class ArrayAssignmentStatement extends Statement {
    private ArrayReference a;
    private Expression e;

    public ArrayAssignmentStatement(ArrayReference a, Expression e) {
        this.a = a;
        this.e = e;
    }

    public ArrayReference getArrayReference() {
        return this.a;
    }

    public Expression getExpression() {
        return this.e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
