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

    public Temp getTemp(Type type, TempClass tempClass, String name) {
        Temp temp = new Temp(type, this.temps.size(), tempClass, name);
        this.temps.add(temp);
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Temp t : this.temps) {
            sb.append("    TEMP ");
            sb.append(t.getNumber());
            sb.append(":");
            sb.append(t.getType().toChar());
            if (t.getTempClass() == TempClass.PARAMETER) {
                sb.append("\t[P(\"");
                sb.append(t.getName());
                sb.append("\")]");
            } else if (t.getTempClass() == TempClass.LOCAL) {
                sb.append("\t[L(\"");
                sb.append(t.getName());
                sb.append("\")]");
            }
            sb.append(";\n");
        }
        if (this.temps.size() > 0) {
            sb.append("\n");
        }
        return sb.toString();
    }
}
