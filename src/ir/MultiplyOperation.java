package ir;

public abstract class MultiplyOperation extends BinaryOperation {
    public MultiplyOperation(Type type, Temp temp1, Temp temp2) {
        super(temp1, temp2, type, "*");
    }
}
