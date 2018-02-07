package ast;

public class IfStatement extends Statement {
    Expression e;
    Block b;
    
    public IfStatement(Expression e, Block b) {
        this.e = e;
        this.b = b;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
