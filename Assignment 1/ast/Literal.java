package ast;

public abstract class Literal extends Expression {
    public abstract void accept(Visitor v);
}
