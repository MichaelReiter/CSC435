package ir;

import type.Type;

public class LessThanOperation extends BinaryOperation {
    public LessThanOperation(Type type, Temp temp1, Temp temp2) {
        super(temp1, temp2, type, "<");
    }
}
