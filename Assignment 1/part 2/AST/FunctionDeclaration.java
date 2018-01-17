package ast;

public class FunctionDeclaration {
    public FunctionDeclaration() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
