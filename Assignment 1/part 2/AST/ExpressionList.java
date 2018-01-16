package AST;

public class ExpressionList {
    public ExpressionList() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
