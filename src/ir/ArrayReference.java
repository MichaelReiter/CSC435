package ir;

import java.lang.StringBuilder;

public class ArrayReference {
    private final Operand identifer;
    private final Operand index;

    public ArrayReference(Operand identifer, Operand index) {
        this.identifer = identifer;
        this.index = index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifer);
        sb.append("[");
        sb.append(this.index);
        sb.append("]");
        return sb.toString();
    }
}
