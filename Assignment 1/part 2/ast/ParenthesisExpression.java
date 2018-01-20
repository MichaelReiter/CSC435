package ast;

public class ParenthesisExpression extends Expression {
    public ParenthesisExpression() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
