package ir;

import java.lang.StringBuilder;
import java.util.List;

public class CallInstruction extends Instruction {
    private final String functionName;
    private final List<Temp> functionArguments;

    public CallInstruction(String functionName, List<Temp> functionArguments) {
        this.functionName = functionName;
        this.functionArguments = functionArguments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append(this.functionName);
        sb.append("(");
        for (Temp t : this.functionArguments) {
            sb.append(t.getType().toChar());
            sb.append(" ");
        }
        sb.append(");");
        return sb.toString();
    }
}
