package ast;

public class BooleanLiteral extends Literal {
    private final boolean value;

    public BooleanLiteral(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
