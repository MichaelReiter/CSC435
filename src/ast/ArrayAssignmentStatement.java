package ast;

public class ArrayAssignmentStatement extends Statement {
    private final ArrayReference a;
    private final Expression e;

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

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
