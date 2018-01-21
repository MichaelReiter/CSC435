package ast;

public class FunctionDeclaration extends ASTNode {
    Declaration d;
    FormalParameters args;

    public FunctionDeclaration(Declaration d, FormalParameters args) {
        this.d = d;
        this.args = args;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
