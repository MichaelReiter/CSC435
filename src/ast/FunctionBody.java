package ast;

import java.util.ArrayList;
import java.util.List;
import type.Type;

public class FunctionBody extends ASTNode {
    private final List<VariableDeclaration> variableDeclarations;
    private StatementList sl;

    public FunctionBody() {
        this.variableDeclarations = new ArrayList<VariableDeclaration>();
    }

    public List<VariableDeclaration> getVariableDeclarations() {
        return this.variableDeclarations;
    }

    public StatementList getStatementList() {
        return this.sl;
    }

    public void setStatementList(StatementList sl) {
        this.sl = sl;
    }

    public void addVariableDeclaration(VariableDeclaration vd) {
        this.variableDeclarations.add(vd);
    }

    public int size() {
        return this.variableDeclarations.size();
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
}
