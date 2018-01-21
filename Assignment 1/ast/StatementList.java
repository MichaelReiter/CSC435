package ast;

import java.util.Vector;

public class StatementList {
    public Vector<Statement> l;

    public StatementList() {
        this.l = new Vector<Statement>();
    }

    public void addStatement(Statement s) {
        this.l.addElement(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
