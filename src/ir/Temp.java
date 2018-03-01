package ir;

import type.Type;

public class Temp {
    private final int number;
    private final Type type;
    private boolean inUse;
    private TempClass tempClass;
    private String name;

    public Temp() {
        
    }

    public enum TempClass {
        PARAMETER,
        LOCAL,
        TEMP
    }

    public Temp getTemp(Type type) {
        return null;
    }

    public Temp getTemp(Type type, TempClass tempClass, String name) {
        return null;
    }

    public String toString() {
        return "";
    }
}
