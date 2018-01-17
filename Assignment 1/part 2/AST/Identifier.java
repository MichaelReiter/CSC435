package ast;

public class Identifier {
    public Identifier() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
