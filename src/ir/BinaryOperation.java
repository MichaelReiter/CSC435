package ir;

import java.lang.StringBuilder;
import type.Type;

public abstract class BinaryOperation extends Operand {
    private final Type type;
    private final Temp leftOperand;
    private final Temp rightOperand;
    private final String operator;

    public BinaryOperation(Type type, Temp leftOperand, Temp rightOperand, String operator) {
        this.type = type;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
        this.operator = operator;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.leftOperand);
        sb.append(" ");
        sb.append(this.type.toChar());
        sb.append(this.operator);
        sb.append(" ");
        sb.append(this.rightOperand);
        return sb.toString();
    }
}
