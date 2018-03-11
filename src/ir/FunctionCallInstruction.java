package ir;

import java.lang.StringBuilder;
import java.util.List;

public class FunctionCallInstruction extends Instruction {
    private final String functionName;
    private final List<Temp> functionArguments;

    public FunctionCallInstruction(String functionName, List<Temp> functionArguments) {
        this.functionName = functionName;
        this.functionArguments = functionArguments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CALL ");
        sb.append(this.functionName);
        sb.append("(");
        for (int i = 0; i < this.functionArguments.size(); i++) {
            sb.append(this.functionArguments.get(i));
            if (i != this.functionArguments.size() - 1) {
                sb.append(" ");
            }
        }
        sb.append(");");
        return sb.toString();
    }
}
