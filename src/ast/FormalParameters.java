package ast;

import java.util.ArrayList;

public class FormalParameters extends ASTNode {
    private ArrayList<Declaration> parameters;

    public FormalParameters() {
        this.parameters = new ArrayList<Declaration>();
    }

    public ArrayList<Declaration> getParameters() {
        return this.parameters;
    }

    public void add(Declaration d) {
        this.parameters.add(d);
    }

    public Declaration get(int index) {
        return (Declaration)this.parameters.get(index);
    }

    public int size() {
        return this.parameters.size();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
