package ir;

public class Label extends Instruction {
    private final int number;

    public Label(int number) {
        this.number = number;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("L");
        sb.append(this.number);
        sb.append(":;");
        return sb.toString();
    }
}
