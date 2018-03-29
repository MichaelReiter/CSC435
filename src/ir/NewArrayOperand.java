package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;
import type.Type;

public class NewArrayOperand extends Operand {
    private final Type type;
    private final int length;

    public NewArrayOperand(Type type, int length) {
        this.type = type;
        this.length = length;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NEWARRAY ");
        sb.append(this.type.toChar());
        sb.append(" ");
        sb.append(this.length);
        return sb.toString();
    }
}
