package ast;

public class VariableDeclaration extends ASTNode {
    public VariableDeclaration() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
