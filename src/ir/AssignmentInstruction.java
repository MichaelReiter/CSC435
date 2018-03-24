package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;

public class AssignmentInstruction extends Instruction {
    private final Operand operand1;
    private final Operand operand2;

    public AssignmentInstruction(Operand operand1, Operand operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.operand1);
        sb.append(" := ");
        sb.append(this.operand2);
        sb.append(";");
        return sb.toString();
    }
}
