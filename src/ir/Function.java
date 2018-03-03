package ir;

import java.util.ArrayList;
import java.util.List;

import type.BooleanType;
import type.CharType;
import type.FloatType;
import type.IntegerType;
import type.StringType;
import type.Type;
import type.VoidType;

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

    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("FUNC ");
        sb.append(this.name);
        sb.append(" (");
        for (int i = 0; i < this.parameterTypes.size(); i++) {
            sb.append(this.typeToChar(this.parameterTypes.get(i)));
            if (i != this.parameterTypes.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        sb.append(this.typeToChar(this.returnType));
        sb.append("\n{\n");
        // Instructions here
        sb.append("}\n");
        return sb.toString();
    }

    private char typeToChar(Type type) {
        if (type.equals(new BooleanType())) {
            return 'Z';
        } else if (type.equals(new CharType())) {
            return 'C';
        } else if (type.equals(new FloatType())) {
            return 'F';
        } else if (type.equals(new IntegerType())) {
            return 'I';
        } else if (type.equals(new StringType())) {
            return 'S';
        } else if (type.equals(new VoidType())) {
            return 'V';
        } else {
            return 'A';
        }
    }
}
