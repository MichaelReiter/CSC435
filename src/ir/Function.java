package ir;

public class Function {
    private String name;
    private String signature;  // ?
    private List<Instruction> instructions;
    private TempFactory tempFactory;

    public Function() {

    }

    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("FUNC ");
        sb.append(this.name);
        sb.append(" ");
        sb.append(this.signature);
        sb.append("\n");
        sb.append("{");
        // Instructions here
        sb.append("}");
        String result = sb.toString();
        return result;
    }
}
