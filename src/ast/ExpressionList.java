package ast;

import java.util.Vector;

public class ExpressionList extends ASTNode {
    private Vector<Expression> l;

    public ExpressionList() {
        this.l = new Vector<Expression>();
    }

    public Vector<Expression> getExpressionList() {
        return this.l;
    }

    public void addElement(Expression e) {
        this.l.addElement(e);
    }

    public Expression elementAt(int index) {
        return (Expression)this.l.elementAt(index);
    }

    public int size() {
        return this.l.size();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
