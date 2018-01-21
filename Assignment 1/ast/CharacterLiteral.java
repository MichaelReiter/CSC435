package ast;

public class CharacterLiteral extends Literal {
    public CharacterLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
