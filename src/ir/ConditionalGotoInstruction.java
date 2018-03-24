package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;

public class ConditionalGotoInstruction extends Instruction {
    private final Temp condition;
    private final Label label;

    public ConditionalGotoInstruction(Temp condition, Label label) {
        this.condition = condition;
        this.label = label;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IF ");
        sb.append(this.condition);
        sb.append(" GOTO L");
        sb.append(this.label.getNumber());
        sb.append(";");
        return sb.toString();
    }
}
