package ir;

public class ConditionalGotoInstruction extends Instruction {
    private Temp condition;
    private Label label;

    public ConditionalGotoInstruction(Temp condition, Label label) {
        this.condition = condition;
        this.label = label;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IF ");
        sb.append(this.condition);
        sb.append(" GOTO ");
        sb.append(this.label);
        sb.append(";");
        return sb.toString();
    }
}
