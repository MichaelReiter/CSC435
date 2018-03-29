package ir;

public abstract class Constant<T> extends Operand {
    protected T value;

    public final T getValue() {
        return this.value;
    }
}
