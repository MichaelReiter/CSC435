package ir;

import java.lang.StringBuilder;

public class ReturnInstruction extends Instruction {
    private final Temp temp;

    public ReturnInstruction(Temp temp) {
        this.temp = temp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RETURN ");
        sb.append(this.temp);
        sb.append(";");
        return sb.toString();
    }
}
