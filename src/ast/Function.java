package ast;

public class Function extends ASTNode {
    private final FunctionDeclaration fd;
    private final FunctionBody fb;

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

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
