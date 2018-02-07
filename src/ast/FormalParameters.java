package ast;

import java.util.Vector;

public class FormalParameters extends ASTNode {
    private Vector<Declaration> parameters;

    public FormalParameters() {
        this.parameters = new Vector<Declaration>();
    }

    public Vector<Declaration> getParameters() {
        return this.parameters;
    }

    public void addElement(Declaration d) {
        this.parameters.addElement(d);
    }

    public Declaration elementAt(int index) {
        return (Declaration)this.parameters.elementAt(index);
    }

    public int size() {
        return this.parameters.size();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
