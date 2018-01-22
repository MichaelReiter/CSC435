package ast;

public abstract class AddOrSubtractExpression extends LessThanExpression {
    public abstract void accept(Visitor v);
}
