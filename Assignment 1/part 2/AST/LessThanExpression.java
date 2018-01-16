package AST;

public class LessThanExpression extends Expression {
    public LessThanExpression() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
