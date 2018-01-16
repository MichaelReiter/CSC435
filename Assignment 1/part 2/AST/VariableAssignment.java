package AST;

public class VariableAssignment {
    public VariableAssignment() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
