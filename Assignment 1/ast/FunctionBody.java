package ast;

import java.util.Vector;

public class FunctionBody extends ASTNode {
    public Vector<VariableDeclaration> variableDeclarations;
    // public StatementList

    public FunctionBody() {
        this.variableDeclarations = new Vector<VariableDeclaration>();
        // this.statements = new Vector<Statement>();
    }

    public void addVariableDeclaration(VariableDeclaration vd) {
        this.variableDeclarations.addElement(vd);
    }

    // public VariableDeclaration elementAt(int index) {
    //     return (VariableDeclaration)this.variableDeclarations.elementAt(index);
    // }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
