package ir;

import type.Type;

public class AddOperation extends BinaryOperation {
    public AddOperation(Type type, Temp leftOperand, Temp rightOperand) {
        super(type, leftOperand, rightOperand, "+");
    }
}
