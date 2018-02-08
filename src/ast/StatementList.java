package ast;

import java.util.ArrayList;
import java.util.List;
import type.Type;

public class StatementList {
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

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
