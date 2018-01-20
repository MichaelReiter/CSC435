package ast;

public class FunctionCall extends Expression {
    public FunctionCall() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
