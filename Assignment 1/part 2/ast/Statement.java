package ast;

public class Statement extends ASTNode {
    public Statement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
