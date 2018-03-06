package ir;

import java.lang.StringBuilder;

public class Label extends Instruction {
    private final int number;

    public Label(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
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
