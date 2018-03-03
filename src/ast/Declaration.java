package ast;

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

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
