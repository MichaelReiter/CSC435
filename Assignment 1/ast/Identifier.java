package ast;

public class Identifier extends Expression {
    public Identifier() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
