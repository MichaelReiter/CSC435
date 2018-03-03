package ast;

public class IntegerLiteral extends Literal {
    private final int value;

    public IntegerLiteral(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
