package ir;

import java.lang.StringBuilder;
import type.Type;

public class Temp extends Operand {
    private final Type type;
    private final int number;
    private final TempClass tempClass;
    private boolean inUse;

    public enum TempClass {
        PARAMETER,
        LOCAL,
        TEMP
    };

    public Temp(Type type, int number, TempClass tempClass) {
        this.type = type;
        this.number = number;
        this.tempClass = tempClass;
    }

    public boolean isParameterOrLocal() {
        return this.tempClass == TempClass.PARAMETER || this.tempClass == TempClass.LOCAL;
    }

    public Type getType() {
        return this.type;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T");
        sb.append(this.number);
        return sb.toString();
    }
}
