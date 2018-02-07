package ast;

public class StringLiteral extends Literal {
    private String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
