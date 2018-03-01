package ir;

public class ConditionalGotoInstruction extends Instruction {
    private Temp condition;
    private Label label;

    public ConditionalGotoInstruction() {

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IF ");
        sb.append(this.condition);
        sb.append(" GOTO ");
        sb.append(this.label);
        sb.append(";");
        String result = sb.toString();
        return result;
    }
}
