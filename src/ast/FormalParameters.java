package ast;

import java.util.ArrayList;
import java.util.List;

public class FormalParameters extends ASTNode {
    private final List<Declaration> parameters;

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
        return this.parameters.get(index);
    }

    public int size() {
        return this.parameters.size();
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
