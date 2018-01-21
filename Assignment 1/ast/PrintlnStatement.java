package ast;

public class PrintlnStatement extends Statement {
    Expression e;

    public PrintlnStatement(Expression e) {
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
