package ir;

import java.util.ArrayList;
import java.util.List;

public class Program extends Instruction {
    private final String name;
    private final List<Function> functions;

    public Program(String name) {
        this.name = name;
        this.functions = new ArrayList<Function>();
    }

    public String getName() {
        return this.name;
    }

    public List<Function> getFunctions() {
        return this.functions;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("PROG ");
        sb.append(this.name);
        return sb.toString();
    }
}
