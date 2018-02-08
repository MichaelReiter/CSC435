package ast;

import java.util.ArrayList;

public class ExpressionList extends ASTNode {
    private ArrayList<Expression> l;

    public ExpressionList() {
        this.l = new ArrayList<Expression>();
    }

    public ArrayList<Expression> getExpressionList() {
        return this.l;
    }

    public void add(Expression e) {
        this.l.add(e);
    }

    public Expression get(int index) {
        return (Expression)this.l.get(index);
    }

    public int size() {
        return this.l.size();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
