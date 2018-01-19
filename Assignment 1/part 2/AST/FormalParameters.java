package ast;

public class FormalParameters extends ASTNode {
    public FormalParameters() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
