package ast;

public class BooleanLiteral extends Expression {
    public BooleanLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
