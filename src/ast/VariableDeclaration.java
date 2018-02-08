package ast;

public class VariableDeclaration extends ASTNode {
    private final Declaration d;

    public VariableDeclaration(Declaration d) {
        this.d = d;
    }

    public Declaration getDeclaration() {
        return this.d;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
