package ast;

public class FunctionCall {
    public FunctionCall() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
