package ir;

import java.lang.StringBuilder;
import type.Type;

public abstract class UnaryOperation extends Operand {
    private final Temp operand;
    private final Type type;
    private final String operator;

    public UnaryOperation(Type type, Temp operand, String operator) {
        this.operand = operand;
        this.type = type;
        this.operator = operator;
    }

    public Temp getOperand() {
        return this.operand;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.toChar());
        sb.append(this.operator);
        sb.append(" ");
        sb.append(this.operand);
        return sb.toString();
    }
}
