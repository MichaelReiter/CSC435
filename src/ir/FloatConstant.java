package ir;

public class FloatConstant extends Constant {
    private final float value;

    public FloatConstant(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }
}
