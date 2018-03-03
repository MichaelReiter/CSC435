package ast;

public class ArrayAssignmentStatement extends Statement {
    private final ArrayReference arrayReference;
    private final Expression expression;

    public ArrayAssignmentStatement(ArrayReference arrayReference, Expression expression) {
        this.arrayReference = arrayReference;
        this.expression = expression;
    }

    public ArrayReference getArrayReference() {
        return this.arrayReference;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
