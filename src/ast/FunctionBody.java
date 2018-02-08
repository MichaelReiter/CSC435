package ast;

import java.util.ArrayList;

public class FunctionBody extends ASTNode {
    private ArrayList<VariableDeclaration> variableDeclarations;
    private StatementList sl;

    public FunctionBody() {
        this.variableDeclarations = new ArrayList<VariableDeclaration>();
    }

    public ArrayList<VariableDeclaration> getVariableDeclarations() {
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

    public void accept(Visitor v) {
        v.visit(this);
    }
}
