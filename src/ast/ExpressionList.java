package ast;

import java.util.ArrayList;
import java.util.List;

public class ExpressionList extends ASTNode {
    private final List<Expression> expressions;

    public ExpressionList() {
        this.expressions = new ArrayList<Expression>();
    }

    public List<Expression> getExpressions() {
        return this.expressions;
    }

    public void add(Expression e) {
        this.expressions.add(e);
    }

    public Expression get(int index) {
        return this.expressions.get(index);
    }

    public int size() {
        return this.expressions.size();
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
