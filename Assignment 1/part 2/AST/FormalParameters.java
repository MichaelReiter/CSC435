package ast;

public class FormalParameters {
    public FormalParameters() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
