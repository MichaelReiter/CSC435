package ast;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList extends ASTNode {
    private List<Expression> l;

    public ExpressionList() {
        this.l = new ArrayList<Expression>();
    }

    public List<Expression> getExpressionList() {
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
