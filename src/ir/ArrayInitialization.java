package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;
import type.ArrayType;

public class ArrayInitialization extends Operand {
    private final ArrayType type;

    public ArrayInitialization(ArrayType type) {
        this.type = type;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NEWARRAY ");
        sb.append(this.type.getType().toChar());
        sb.append(" ");
        sb.append(this.type.getSize());
        return sb.toString();
    }
}
