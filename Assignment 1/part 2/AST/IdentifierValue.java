package AST;

public class IdentifierValue {
    public IdentifierValue() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
