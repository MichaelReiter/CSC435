package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;

public class Label extends Instruction {
    private final int number;

    public Label(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("L");
        sb.append(this.number);
        sb.append(":;");
        return sb.toString();
    }
}
