package ir;

import java.lang.StringBuilder;
import type.Type;

public abstract class UnaryOperation extends Instruction {
    private final Temp temp;
    private final Type type;
    private final String operator;

    public UnaryOperation(Temp temp, Type type, String operator) {
        this.temp = temp;
        this.type = type;
        this.operator = operator;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.temp);
        sb.append(" ");
        sb.append(this.type.toChar());
        sb.append(this.operator);
        sb.append(";");
        return sb.toString();
    }
}
