package ast;

public abstract class Atom extends MultiplyExpression {
    public abstract void accept(Visitor v);
}
