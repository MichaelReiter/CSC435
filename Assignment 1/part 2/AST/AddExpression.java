package AST;

public class AddExpression extends Expression {
    public AddExpression() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
