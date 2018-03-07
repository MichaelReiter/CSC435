package ir;

import type.Type;

public class LessThanOperation extends BinaryOperation {
    public LessThanOperation(Type type, Temp leftOperand, Temp rightOperand) {
        super(type, leftOperand, rightOperand, "<");
    }
}
