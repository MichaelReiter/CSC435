package ir;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import type.Type;

public class Function extends Instruction {
    private final String name;
    private final Type returnType;
    private final List<Type> parameterTypes;
    private final List<Instruction> instructions;
    private final TempFactory tempFactory;
    
    public Function(String name, Type returnType, List<Type> parameterTypes) {
        this.name = name;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.instructions = new ArrayList<Instruction>();
        this.tempFactory = new TempFactory();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("FUNC ");
        sb.append(this.name);
        sb.append(" (");
        for (int i = 0; i < this.parameterTypes.size(); i++) {
            sb.append(this.parameterTypes.get(i).toChar());
            if (i != this.parameterTypes.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        sb.append(this.returnType.toChar());
        sb.append("\n{\n");
        // Instructions here
        sb.append("}\n");
        return sb.toString();
    }
}
