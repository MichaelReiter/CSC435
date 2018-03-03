package ast;

public class Function extends ASTNode {
    private final FunctionDeclaration functionDeclaration;
    private final FunctionBody functionBody;

    public Function(FunctionDeclaration functionDeclaration, FunctionBody functionBody) {
        this.functionDeclaration = functionDeclaration;
        this.functionBody = functionBody;
    }

    public FunctionDeclaration getFunctionDeclaration() {
        return this.functionDeclaration;
    }

    public FunctionBody getFunctionBody() {
        return this.functionBody;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
