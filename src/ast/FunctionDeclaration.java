package ast;

public class FunctionDeclaration extends ASTNode {
    private final Declaration d;
    private final FormalParameters args;

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

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
