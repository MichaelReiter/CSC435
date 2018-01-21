package ast;

public class StringLiteral extends Literal {
    public StringLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
