package ast;

public class BooleanLiteral extends Literal {
    public BooleanLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
