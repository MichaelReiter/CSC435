package ast;

import java.util.ArrayList;
import java.util.List;

public class Program extends ASTNode {
    private final List<Function> functionList;

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
        return this.functionList.get(index);
    }

    public int size() {
        return this.functionList.size();
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
