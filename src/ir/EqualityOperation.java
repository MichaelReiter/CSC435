package ir;

public abstract class EqualityOperation extends BinaryOperation {
    public EqualityOperation(Type type, Temp temp1, Temp temp2) {
        super(temp1, temp2, type, "==");
    }
}
