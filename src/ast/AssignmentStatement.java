package ast;

public class AssignmentStatement extends Statement {
    private Identifier id;
    private Expression e;

    public AssignmentStatement(Identifier id, Expression e) {
        this.id = id;
        this.e = e;
    }

    public Identifier getIdentifier() {
        return this.id;
    }

    public Expression getExpression() {
        return this.e;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
