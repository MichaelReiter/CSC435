package ir;

import java.lang.StringBuilder;

public class AssignmentOperation {
    private final Temp t1;
    private final Temp t2;

    public AssignmentOperation(Temp t1, Temp t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.t1);
        sb.append(" := ");
        sb.append(this.t2);
        sb.append(";");
        return sb.toString();
    }
}
