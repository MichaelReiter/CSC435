package ir;

import type.Type;

public class SubtractOperation extends BinaryOperation {
    public SubtractOperation(Type type, Temp leftOperand, Temp rightOperand) {
        super(type, leftOperand, rightOperand, "-");
    }
}
