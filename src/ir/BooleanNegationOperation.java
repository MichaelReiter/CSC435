package ir;

import type.BooleanType;

public class BooleanNegationOperation extends UnaryOperation {
    public BooleanNegationOperation(Temp temp) {
        super(temp, new BooleanType(), "!");
    }
}
