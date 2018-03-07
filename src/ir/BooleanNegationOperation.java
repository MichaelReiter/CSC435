package ir;

import type.BooleanType;

public class BooleanNegationOperation extends UnaryOperation {
    public BooleanNegationOperation(Temp operand) {
        super(new BooleanType(), operand, "!");
    }
}
