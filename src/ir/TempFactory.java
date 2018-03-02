package ir;

import type.Type;
import ir.Temp.TempClass;

public class TempFactory {
    private int count;

    public TempFactory() {
        this.count = 0;
    }

    public Temp getTemp(Type type) {
        Temp temp = new Temp(type, this.count);
        this.count++;
        return temp;
    }

    public Temp getTemp(Type type, TempClass tempClass, String name) {
        return null;
    }
}
