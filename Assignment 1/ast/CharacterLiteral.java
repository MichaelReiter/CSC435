package ast;

public class CharacterLiteral extends Literal {
    char value;

    public CharacterLiteral(char value) {
        this.value = value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
