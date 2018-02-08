package ast;

import java.util.ArrayList;
import java.util.List;

public class StatementList {
    private List<Statement> l;

    public StatementList() {
        this.l = new ArrayList<Statement>();
    }

    public List<Statement> getStatements() {
        return this.l;
    }

    public void addStatement(Statement s) {
        this.l.add(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
