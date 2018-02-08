package ast;

public class FloatLiteral extends Literal {
    private final float value;

    public FloatLiteral(float value) {
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
