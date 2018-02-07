package ast;

import java.util.Vector;

public class FunctionBody extends ASTNode {
    private Vector<VariableDeclaration> variableDeclarations;
    private StatementList sl;

    public FunctionBody() {
        this.variableDeclarations = new Vector<VariableDeclaration>();
    }

    public Vector<VariableDeclaration> getVariableDeclarations() {
        return this.variableDeclarations;
    }

    public StatementList getStatementList() {
        return this.sl;
    }

    public void addVariableDeclaration(VariableDeclaration vd) {
        this.variableDeclarations.addElement(vd);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
