package ir;

import java.lang.StringBuilder;

public abstract class BinaryOperation extends Instruction {
    private final Temp temp1;
    private final Temp temp2;
    private final Type type;
    private final String operator;

    public BinaryOperation(Temp temp1, Temp temp2, Type type, String operator) {
        this.temp1 = temp1;
        this.temp2 = temp2;
        this.type = type;
        this.operator = operator;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.temp1);
        sb.append(" ");
        sb.append(this.type.toChar());
        sb.append(this.operator);
        sb.append(" ");
        sb.append(this.temp2);
        sb.append(";");
        return sb.toString();
    }
}
