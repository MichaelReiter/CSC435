package ast;

import java.util.ArrayList;
import java.util.List;

public class FormalParameters extends ASTNode {
    private List<Declaration> parameters;

    public FormalParameters() {
        this.parameters = new ArrayList<Declaration>();
    }

    public List<Declaration> getParameters() {
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
