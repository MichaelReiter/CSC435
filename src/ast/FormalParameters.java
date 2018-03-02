package ast;

import ir.Temp;
import ir.TempVisitor;
import java.util.ArrayList;
import java.util.List;
import type.Type;

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
        return (Declaration)this.parameters.get(index);
    }

    public int size() {
        return this.parameters.size();
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
