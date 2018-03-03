package ast;

public class Declaration extends ASTNode {
    private final TypeNode type;
    private final Identifier identifier;

    public Declaration(TypeNode type, Identifier identifier) {
        this.type = type;
        this.identifier = identifier;
    }

    public TypeNode getType() {
        return this.type;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
