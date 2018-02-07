package ast;

public class FunctionDeclaration extends ASTNode {
    private Declaration d;
    private FormalParameters args;

    public FunctionDeclaration(Declaration d, FormalParameters args) {
        this.d = d;
        this.args = args;
    }

    public Declaration getDeclaration() {
        return this.d;
    }

    public FormalParameters getFormalParameters() {
        return this.args;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
