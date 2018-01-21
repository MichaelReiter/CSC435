package ast;

public class ParenthesisExpression extends Atom {
    Expression e;

    public ParenthesisExpression(Expression e) {
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
