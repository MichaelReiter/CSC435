package ast;

public class FloatLiteral {
    public FloatLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
