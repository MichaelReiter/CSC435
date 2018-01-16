package AST;

public class Expression {
    public Expression() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
