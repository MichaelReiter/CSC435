package ast;

public class StringLiteral extends Literal {
    String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
