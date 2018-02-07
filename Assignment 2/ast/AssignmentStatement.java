package ast;

public class AssignmentStatement extends Statement {
    Identifier id;
    Expression e;

    public AssignmentStatement(Identifier id, Expression e) {
        this.id = id;
        this.e = e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
