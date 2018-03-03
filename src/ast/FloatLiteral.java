package ast;

public class FloatLiteral extends Literal {
    private final float value;

    public FloatLiteral(float value) {
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
