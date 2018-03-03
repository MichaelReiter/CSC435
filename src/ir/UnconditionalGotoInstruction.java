package ir;

import java.lang.StringBuilder;

public class UnconditionalGotoInstruction extends Instruction {
    private final Label label;

    public UnconditionalGotoInstruction(Label label) {
        this.label = label;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GOTO ");
        sb.append(this.label);
        sb.append(";");
        return sb.toString();
    }
}
