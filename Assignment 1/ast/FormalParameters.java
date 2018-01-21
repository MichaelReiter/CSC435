package ast;

import java.util.Vector;

public class FormalParameters extends ASTNode {
    public Vector<Declaration> parameters;

    public FormalParameters() {
        parameters = new Vector<Declaration>();
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
