package AST;

public class SubtractExpression extends Expression {
    public SubtractExpression() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
