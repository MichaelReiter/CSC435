package ast;

public class IfElseStatement extends Statement {
    Expression e;
    Block b1;
    Block b2;
    
    public IfElseStatement(Expression e, Block b1, Block b2) {
        this.e = e;
        this.b1 = b1;
        this.b2 = b2;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
