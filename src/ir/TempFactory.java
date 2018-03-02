package ir;

import type.Type;

public class TempFactory {
    private int count;

    public TempFactory() {
        this.count = 0;
    }

    public Temp getTemp(Type type) {
        Temp temp = new Temp(type, this.count);
        this.count++;
    }

    public Temp getTemp(Type type, TempClass tempClass, String name) {
        return null;
    }
}
