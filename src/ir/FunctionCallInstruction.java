package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;
import java.util.List;
import type.Type;

public class FunctionCallInstruction extends Instruction {
    private final String functionName;
    private final List<Temp> functionArguments;
    private final Type functionReturnType;

    public FunctionCallInstruction(String functionName,
                                   List<Temp> functionArguments,
                                   Type functionReturnType) {
        this.functionName = functionName;
        this.functionArguments = functionArguments;
        this.functionReturnType = functionReturnType;
    }

    public String getFunctionName() {
        return this.functionName;
    }

    public List<Temp> getFunctionArguments() {
        return this.functionArguments;
    }

    public Type getFunctionReturnType() {
        return this.functionReturnType;
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
        sb.append(");");
        return sb.toString();
    }
}
