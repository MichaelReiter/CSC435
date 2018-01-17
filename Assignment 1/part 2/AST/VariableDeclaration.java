package ast;

public class VariableDeclaration {
    public VariableDeclaration() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
