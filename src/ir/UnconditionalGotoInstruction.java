package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;

public class UnconditionalGotoInstruction extends Instruction {
    private final Label label;

    public UnconditionalGotoInstruction(Label label) {
        this.label = label;
    }

    public Label getLabel() {
        return this.label;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
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
