package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class FunctionDeclaration extends ASTNode {
    private final Declaration d;
    private final FormalParameters args;

    public FunctionDeclaration(Declaration d, FormalParameters args) {
        this.d = d;
        this.args = args;
    }

    public Declaration getDeclaration() {
        return this.d;
    }

    public FormalParameters getFormalParameters() {
        return this.args;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
