package ast;

public class VariableDeclaration extends ASTNode {
    private final Declaration declaration;

    public VariableDeclaration(Declaration declaration) {
        this.declaration = declaration;
    }

    public Declaration getDeclaration() {
        return this.declaration;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
