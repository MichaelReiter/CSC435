package ir;

import java.lang.StringBuilder;

public class AssignmentOperation {
    private final Temp temp1;
    private final Temp temp2;

    public AssignmentOperation(Temp temp1, Temp temp2) {
        this.temp1 = temp1;
        this.temp2 = temp2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.temp1);
        sb.append(" := ");
        sb.append(this.temp2);
        sb.append(";");
        return sb.toString();
    }
}
