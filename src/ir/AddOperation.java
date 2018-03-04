package ir;

import type.Type;

public class AddOperation extends BinaryOperation {
    public AddOperation(Type type, Temp temp1, Temp temp2) {
        super(temp1, temp2, type, "+");
    }
}
