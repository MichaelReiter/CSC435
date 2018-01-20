package ast;

public class FunctionBody extends ASTNode {
    public FunctionBody() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
