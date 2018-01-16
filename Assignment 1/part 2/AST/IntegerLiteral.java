package AST;

public class IntegerLiteral {
    public IntegerLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
