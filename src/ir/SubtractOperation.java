package ir;

public abstract class Subtract extends BinaryOperation {
    public Subtract(Type type, Temp temp1, Temp temp2) {
        super(temp1, temp2, type, "-");
    }
}
