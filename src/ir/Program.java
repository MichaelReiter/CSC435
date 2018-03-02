package ir;

public class Program {
    private String name;
    private List<Function> functions;

    public Program() {
        
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
