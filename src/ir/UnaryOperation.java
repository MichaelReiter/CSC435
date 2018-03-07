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

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.operand);
        sb.append(" ");
        sb.append(this.type.toChar());
        sb.append(this.operator);
        return sb.toString();
    }
}
