package AST;

public class FunctionDeclaration {
    public FunctionDeclaration() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
