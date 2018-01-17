package ast;

public class AssignmentStatement extends Statement {
    public AssignmentStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
