package ast;

public class CharacterLiteral extends Literal {
    private char value;

    public CharacterLiteral(char value) {
        this.value = value;
    }

    public char getValue() {
        return this.value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
