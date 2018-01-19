package ast;

public class Function extends ASTNode {
    public Function() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
