package ast;

public abstract class Literal extends Atom {
    public abstract void accept(Visitor v);
}
