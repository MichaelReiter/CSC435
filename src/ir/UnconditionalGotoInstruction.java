package ir;

import java.lang.StringBuilder;

public class UnconditionalGotoInstruction extends Instruction {
    private final Label label;

    public UnconditionalGotoInstruction(Label label) {
        this.label = label;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GOTO L");
        sb.append(this.label.getNumber());
        sb.append(";");
        return sb.toString();
    }
}
