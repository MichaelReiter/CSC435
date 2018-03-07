package ir;

import type.Type;

public class MultiplyOperation extends BinaryOperation {
    public MultiplyOperation(Type type, Temp leftOperand, Temp rightOperand) {
        super(type, leftOperand, rightOperand, "*");
    }
}
