package ir;

import java.lang.StringBuilder;
import type.Type;

public class PrintInstruction extends Instruction {
    private final Type type;
    private final Temp temp;

    public PrintInstruction(Type type, Temp temp) {
        this.type = type;
        this.temp = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PRINT");
        sb.append(this.type.toChar());
        sb.append(" ");
        sb.append(this.temp);
        sb.append(";");
        return sb.toString();
    }
}
