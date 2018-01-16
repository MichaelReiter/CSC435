package AST;

public class MultiplyExpression extends Expression {
    public MultiplyExpression() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
