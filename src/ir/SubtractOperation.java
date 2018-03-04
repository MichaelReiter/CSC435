package ir;

import type.Type;

public abstract class SubtractOperation extends BinaryOperation {
    public SubtractOperation(Type type, Temp temp1, Temp temp2) {
        super(temp1, temp2, type, "-");
    }
}
