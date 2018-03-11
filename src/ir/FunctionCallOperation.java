package ir;

import java.lang.StringBuilder;
import java.util.List;

public class FunctionCallOperation extends Operand {
    private final String functionName;
    private final List<Temp> functionArguments;

    public FunctionCallOperation(String functionName, List<Temp> functionArguments) {
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
        sb.append(")");
        return sb.toString();
    }
}
