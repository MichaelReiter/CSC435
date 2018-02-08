package ast;

import java.util.ArrayList;

public class StatementList {
    private ArrayList<Statement> l;

    public StatementList() {
        this.l = new ArrayList<Statement>();
    }

    public ArrayList<Statement> getStatements() {
        return this.l;
    }

    public void addStatement(Statement s) {
        this.l.add(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
