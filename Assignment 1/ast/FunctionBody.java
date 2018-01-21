package ast;

import java.util.Vector;

public class FunctionBody extends ASTNode {
    public Vector<VariableDeclaration> variableDeclarations;
    public Vector<Statement> statements;

    public FunctionBody() {
        this.variableDeclarations = new Vector<VariableDeclaration>();
        this.statements = new Vector<Statement>();
    }

    public void addVariableDeclaration(VariableDeclaration vd) {
        this.variableDeclarations.addElement(vd);
    }

    public void addStatement(Statement s) {
        this.statements.addElement(s);
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
