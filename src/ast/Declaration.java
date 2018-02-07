package ast;

public class Declaration {
    private TypeNode type;
    private Identifier id;

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

    public void accept(Visitor v) {
        v.visit(this);
    }
}
