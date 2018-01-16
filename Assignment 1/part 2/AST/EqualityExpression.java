package AST;

public class EqualityExpression extends Expression {
    public EqualityExpression() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
