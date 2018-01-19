package ast;

public class IntegerLiteral extends Expression {
    public IntegerLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
