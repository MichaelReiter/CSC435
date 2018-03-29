package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;

public class AssignmentInstruction extends Instruction {
    private final Temp operand1;
    private final Operand operand2;

    public AssignmentInstruction(Temp operand1, Operand operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Temp getLeftOperand() {
        return this.operand1;
    }

    public Operand getRightOperand() {
        return this.operand2;
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
