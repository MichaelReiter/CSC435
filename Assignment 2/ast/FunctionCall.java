package ast;

public class FunctionCall extends Expression {
    Identifier id;
    ExpressionList el;

    public FunctionCall(Identifier id, ExpressionList el) {
        this.id = id;
        this.el = el;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
