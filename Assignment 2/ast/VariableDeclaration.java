package ast;

public class VariableDeclaration extends ASTNode {
    Declaration d;

    public VariableDeclaration(Declaration d) {
        this.d = d;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
