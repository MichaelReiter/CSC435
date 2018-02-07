package ast;

import java.util.Vector;

public class FunctionBody extends ASTNode {
    public Vector<VariableDeclaration> variableDeclarations;
    public StatementList sl;

    public FunctionBody() {
        this.variableDeclarations = new Vector<VariableDeclaration>();
    }

    public void addVariableDeclaration(VariableDeclaration vd) {
        this.variableDeclarations.addElement(vd);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
