package ir;

public class UnconditionalGotoInstruction extends Instruction {
    private Label label;

    public UnconditionalGotoInstruction() {

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GOTO ");
        sb.append(this.label);
        sb.append(";");
        String result = sb.toString();
        return result;
    }
}
