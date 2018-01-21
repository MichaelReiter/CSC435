package ast;

public class FloatLiteral extends Literal {
    float value;

    public FloatLiteral(float value) {
        this.value = value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
