package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import type.Type;

public class Function extends Instruction {
    private final String name;
    private final Type returnType;
    private final List<Type> parameterTypes;
    private final String signature;
    private TempFactory tempFactory;
    private List<Instruction> instructions;

    public Function(String name, Type returnType, List<Type> parameterTypes) {
        this.name = name;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Type t : parameterTypes) {
            sb.append(t.toChar());
        }
        sb.append(")");
        sb.append(this.returnType.toChar());
        this.signature = sb.toString();
    }

    public void setTempFactory(TempFactory tempFactory) {
        this.tempFactory = tempFactory;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public TempFactory getTempFactory() {
        return this.tempFactory;
    }

    public String getName() {
        return this.name;
    }

    public String getSignature() {
        return this.signature;
    }

    public Type getReturnType() {
        return this.returnType;
    }

    public List<Type> getParameterTypes() {
        return this.parameterTypes;
    }

    public List<Instruction> getInstructions() {
        return this.instructions;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Function signature
        sb.append("FUNC ");
        sb.append(this.name);
        sb.append(" ");
        sb.append(this.signature);
        sb.append("\n{\n");
        // Temp declarations
        sb.append(tempFactory);
        // Instructions
        for (Instruction i : this.instructions) {
            if (i.getClass() != Label.class) {
                sb.append("\t");
            }
            sb.append(i);
            sb.append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
