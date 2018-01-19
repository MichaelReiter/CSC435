package ast;

public class StringLiteral extends Expression {
    public StringLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
