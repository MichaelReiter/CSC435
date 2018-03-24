package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;
import type.Type;

public class Temp extends Operand {
    private final Type type;
    private final int number;
    private final TempClass tempClass;
    private final String name;

    public enum TempClass {
        PARAMETER,
        LOCAL,
        TEMP
    };

    public Temp(Type type, int number, TempClass tempClass) {
        this.type = type;
        this.number = number;
        this.tempClass = tempClass;
        this.name = null;
    }

    public Temp(Type type, int number, TempClass tempClass, String name) {
        this.type = type;
        this.number = number;
        this.tempClass = tempClass;
        this.name = name;
    }

    public boolean isParameterOrLocal() {
        return this.tempClass == TempClass.PARAMETER || this.tempClass == TempClass.LOCAL;
    }

    public Type getType() {
        return this.type;
    }

    public int getNumber() {
        return this.number;
    }

    public TempClass getTempClass() {
        return this.tempClass;
    }

    public String getName() {
        return this.name;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("T");
        sb.append(this.number);
        return sb.toString();
    }
}
