package AST;

public class CharacterLiteral {
    public CharacterLiteral() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
