package ast;

import java.util.Vector;

public class FormalParameters extends ASTNode {
    private Vector<Declaration> parameters;

    public FormalParameters() {
        parameters = new Vector<Declaration>();
    }

    public Vector<Declaration> getParameters() {
        return this.parameters;
    }

    public void addElement(Declaration d) {
        parameters.addElement(d);
    }

    public Declaration elementAt(int index) {
        return (Declaration)parameters.elementAt(index);
    }

    public int size() {
        return parameters.size();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
