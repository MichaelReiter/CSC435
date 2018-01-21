package ast;

import java.util.Vector;

public class Program extends ASTNode {
    public Vector<Function> functionList;

    public Program() {
        this.functionList = new Vector<Function>();
    }

    public void addElement(Function f) {
        this.functionList.addElement(f);
    }

    public Function elementAt(int index) {
        return (Function)this.functionList.elementAt(index);
    }

    public int size() {
        return this.functionList.size();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
