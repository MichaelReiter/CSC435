package ast;

import java.util.ArrayList;
import java.util.List;

public class Program extends ASTNode {
    private List<Function> functionList;

    public Program() {
        this.functionList = new ArrayList<Function>();
    }

    public List<Function> getFunctions() {
        return this.functionList;
    }

    public void add(Function f) {
        this.functionList.add(f);
    }

    public Function get(int index) {
        return (Function)this.functionList.get(index);
    }

    public int size() {
        return this.functionList.size();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
