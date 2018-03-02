package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class VariableDeclaration extends ASTNode {
    private final Declaration d;

    public VariableDeclaration(Declaration d) {
        this.d = d;
    }

    public Declaration getDeclaration() {
        return this.d;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
