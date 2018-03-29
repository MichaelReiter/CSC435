package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;
import java.util.List;

public class FunctionCallOperation extends Operand {
    private final String functionName;
    private final List<Temp> functionArguments;

    public FunctionCallOperation(String functionName, List<Temp> functionArguments) {
        this.functionName = functionName;
        this.functionArguments = functionArguments;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
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
        sb.append(")");
        return sb.toString();
    }
}
