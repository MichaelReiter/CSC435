package AST;

public class BooleanLiteral {
    public BooleanLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
