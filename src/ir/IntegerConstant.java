package ir;

public class IntegerConstant extends Constant {
    private final int value;

    public IntegerConstant(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
