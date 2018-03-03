package ast;

public class FunctionDeclaration extends ASTNode {
    private final Declaration declaration;
    private final FormalParameters formalParameters;

    public FunctionDeclaration(Declaration declaration, FormalParameters formalParameters) {
        this.declaration = declaration;
        this.formalParameters = formalParameters;
    }

    public Declaration getDeclaration() {
        return this.declaration;
    }

    public FormalParameters getFormalParameters() {
        return this.formalParameters;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
