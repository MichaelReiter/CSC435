package ir;

import java.lang.StringBuilder;
import type.Type;

public class TempDeclarationInstruction extends Instruction {
    private final Type type;
    private final int number;

    public TempDeclarationInstruction(Type type, int number) {
        this.type = type;
        this.number = number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TEMP ");
        sb.append(this.number);
        sb.append(":");
        sb.append(this.type.toChar());
        sb.append(";");
        return sb.toString();
    }
}
