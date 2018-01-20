package ast;

public class Function extends ASTNode {
    FunctionDeclaration fd;
    FunctionBody fb;

    public Function(FunctionDeclaration fd, FunctionBody fb) {
        this.fd = fd;
        this.fb = fb;
    }

    public void accept(Visitor v) {
        v.visit(this);
        System.out.println(this);
    }
}
