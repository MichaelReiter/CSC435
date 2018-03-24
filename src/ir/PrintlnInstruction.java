package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;
import type.Type;

public class PrintlnInstruction extends Instruction {
    private final Type type;
    private final Temp temp;

    public PrintlnInstruction(Type type, Temp temp) {
        this.type = type;
        this.temp = temp;
    }

    public Type getType() {
        return this.type;
    }

    public Temp getTemp() {
        return this.temp;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PRINTLN");
        sb.append(this.type.toChar());
        sb.append(" ");
        sb.append(this.temp);
        sb.append(";");
        return sb.toString();
    }
}
