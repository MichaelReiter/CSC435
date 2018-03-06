package ir;

import type.Type;
import ir.Temp.TempClass;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class TempFactory {
    private List<Temp> temps;

    public TempFactory() {
        this.temps = new ArrayList<Temp>();
    }

    public Temp getTemp(Type type, TempClass tempClass) {
        Temp temp = new Temp(type, this.temps.size(), tempClass);
        this.temps.add(temp);
        return temp;
    }

    // public Temp getTemp(Type type, TempClass tempClass, String name) {
    //     return null;
    // }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Temp t : this.temps) {
            sb.append("    TEMP ");
            sb.append(t.getNumber());
            sb.append(":");
            sb.append(t.getType().toChar());
            sb.append(";\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
