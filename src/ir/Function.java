package ir;

import java.util.ArrayList;
import java.util.List;

public class Function {
    private final String name;
    private final List<Instruction> instructions;
    private final TempFactory tempFactory;
    private String signature;  // ?
    
    public Function(String name) {
        this.name = name;
        this.instructions = new ArrayList<Instruction>();
        this.tempFactory = new TempFactory();
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
        return sb.toString();
    }
}
