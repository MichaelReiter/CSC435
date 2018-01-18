package ast;

public class ReturnStatement extends Statement {
    Expression e;

    public ReturnStatement(Expression e) {
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
