package ir;

import ir.Temp.TempClass;
import java.lang.StringBuilder;
import type.ArrayType;

public class ArrayReference extends Temp {
    private final Temp identifier;
    private final Temp index;

    public ArrayReference(Temp identifier, Temp index) {
        super(((ArrayType)identifier.getType()).getType(), 0, TempClass.TEMP);
        this.identifier = identifier;
        this.index = index;
    }

    public Temp getIdentifier() {
        return this.identifier;
    }

    public Temp getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier);
        sb.append("[");
        sb.append(this.index);
        sb.append("]");
        return sb.toString();
    }
}
