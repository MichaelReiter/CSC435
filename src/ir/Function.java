package ir;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import type.Type;

public class Function extends Instruction {
    private final String name;
    private final Type returnType;
    private final List<Type> parameterTypes;
    private TempFactory tempFactory;
    private List<Instruction> instructions;

    public Function(String name, Type returnType, List<Type> parameterTypes) {
        this.name = name;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }

    public void setTempFactory(TempFactory tempFactory) {
        this.tempFactory = tempFactory;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Function signature
        sb.append("FUNC ");
        sb.append(this.name);
        sb.append(" (");
        for (Type t : this.parameterTypes) {
            sb.append(t.toChar());
        }
        sb.append(")");
        sb.append(this.returnType.toChar());
        sb.append("\n{\n");
        // Temp declarations
        sb.append(tempFactory);
        // Instructions
        for (Instruction i : this.instructions) {
            sb.append("    ");
            sb.append(i);
            sb.append("\n");
        }
        sb.append("}\n\n");
        return sb.toString();
    }
}
