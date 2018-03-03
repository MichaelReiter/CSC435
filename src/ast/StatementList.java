package ast;

import java.util.ArrayList;
import java.util.List;

public class StatementList extends ASTNode {
    private final List<Statement> l;

    public StatementList() {
        this.l = new ArrayList<Statement>();
    }

    public List<Statement> getStatements() {
        return this.l;
    }

    public void addStatement(Statement s) {
        this.l.add(s);
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
