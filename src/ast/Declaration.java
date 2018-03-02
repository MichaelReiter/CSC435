package ast;

import ir.Temp;
import ir.TempVisitor;
import type.Type;

public class Declaration extends ASTNode {
    private final TypeNode type;
    private final Identifier id;

    public Declaration(TypeNode type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    public TypeNode getType() {
        return this.type;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
