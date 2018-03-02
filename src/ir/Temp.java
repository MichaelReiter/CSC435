package ir;

import type.Type;

public class Temp {
    private final int number;
    private final Type type;
    private boolean inUse;
    private TempClass tempClass;
    private String name;
    
    public enum TempClass {
        PARAMETER,
        LOCAL,
        TEMP
    }

    public Temp(Type type) {
        this.type = type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T");
        sb.append(this.number);
        return sb.toString();
    }
}
