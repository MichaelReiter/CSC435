package ast;

public class Declaration {
    TypeNode type;
    Identifier id;

    public Declaration(TypeNode type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
