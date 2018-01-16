package AST;

public class StringLiteral {
    public StringLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
