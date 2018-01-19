package ast;

public class FloatLiteral extends Expression {
    public FloatLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
