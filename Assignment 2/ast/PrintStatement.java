package ast;

public class PrintStatement extends Statement {
    Expression e;

    public PrintStatement(Expression e) {
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
