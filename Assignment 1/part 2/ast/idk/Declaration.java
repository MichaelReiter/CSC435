package ast;

public class Declaration {
    public Declaration() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
