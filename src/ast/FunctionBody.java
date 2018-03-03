package ast;

import java.util.ArrayList;
import java.util.List;

public class FunctionBody extends ASTNode {
    private final List<VariableDeclaration> variableDeclarations;
    private StatementList statementList;

    public FunctionBody() {
        this.variableDeclarations = new ArrayList<VariableDeclaration>();
    }

    public List<VariableDeclaration> getVariableDeclarations() {
        return this.variableDeclarations;
    }

    public StatementList getStatementList() {
        return this.statementList;
    }

    public void setStatementList(StatementList statementList) {
        this.statementList = statementList;
    }

    public void addVariableDeclaration(VariableDeclaration variableDeclaration) {
        this.variableDeclarations.add(variableDeclaration);
    }

    public int size() {
        return this.variableDeclarations.size();
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
