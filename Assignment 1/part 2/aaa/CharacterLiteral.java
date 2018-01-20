package ast;

public class CharacterLiteral extends Expression {
    public CharacterLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
