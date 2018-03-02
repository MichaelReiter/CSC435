package ast;

import ir.Temp;
import ir.TempVisitor;
import java.util.ArrayList;
import java.util.List;
import type.Type;

public class ExpressionList extends ASTNode {
    private final List<Expression> l;

    public ExpressionList() {
        this.l = new ArrayList<Expression>();
    }

    public List<Expression> getExpressions() {
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

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
