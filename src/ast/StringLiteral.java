package ast;

public class StringLiteral extends Literal {
    private final String value;

    public StringLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
