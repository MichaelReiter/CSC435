package ir;

import type.Type;

public class EqualityOperation extends BinaryOperation {
    public EqualityOperation(Type type, Temp leftOperand, Temp rightOperand) {
        super(type, leftOperand, rightOperand, "==");
    }
}
