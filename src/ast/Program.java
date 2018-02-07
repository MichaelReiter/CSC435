package ast;

import java.util.Vector;

public class Program extends ASTNode {
    private Vector<Function> functionList;

    public Program() {
        this.functionList = new Vector<Function>();
    }

    public Vector<Function> getFunctionList() {
        return this.functionList;
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
