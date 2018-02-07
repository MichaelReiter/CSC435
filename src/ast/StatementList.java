package ast;

import java.util.Vector;

public class StatementList {
    private Vector<Statement> l;

    public StatementList() {
        this.l = new Vector<Statement>();
    }

    public Vector<Statement> getStatements() {
        return this.l;
    }

    public void addStatement(Statement s) {
        this.l.addElement(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
