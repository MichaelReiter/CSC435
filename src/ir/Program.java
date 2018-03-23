package ir;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class Program extends Instruction {
    private final String name;
    private final List<Function> functions;

    public Program(String name) {
        this.name = name;
        this.functions = new ArrayList<Function>();
    }

    public void addFunction(Function function) {
        this.functions.add(function);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        sb.append("PROG ");
        sb.append(this.name);
        sb.append("\n");
        for (Function f : this.functions) {
            sb.append(f);
            sb.append("\n");
        }
        return sb.toString();
    }
}
