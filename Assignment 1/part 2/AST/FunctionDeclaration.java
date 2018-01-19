package ast;

public class FunctionDeclaration extends ASTNode {
    public FunctionDeclaration() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
