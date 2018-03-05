package ir;

import java.lang.StringBuilder;
import type.Type;

public class Temp extends Operand {
    private final Type type;
    private final int number;
    private final String name;
    private final TempClass tempClass;
    private boolean inUse;

    public enum TempClass {
        PARAMETER,
        LOCAL,
        TEMP
    };

    public Temp(Type type, int number) {
        this.type = type;
        this.number = number;
        this.name = "";
        this.tempClass = TempClass.TEMP;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T");
        sb.append(this.number);
        return sb.toString();
    }
}
