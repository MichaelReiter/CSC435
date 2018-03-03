package ast;

public class CharacterLiteral extends Literal {
    private final char value;

    public CharacterLiteral(char value) {
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
