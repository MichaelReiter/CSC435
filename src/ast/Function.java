package ast;

public class Function extends ASTNode {
    private FunctionDeclaration fd;
    private FunctionBody fb;

    public Function(FunctionDeclaration fd, FunctionBody fb) {
        this.fd = fd;
        this.fb = fb;
    }

    public FunctionDeclaration getFunctionDeclaration() {
        return this.fd;
    }

    public FunctionBody getFunctionBody() {
        return this.fb;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
