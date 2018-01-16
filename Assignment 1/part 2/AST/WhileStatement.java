package AST;

public class WhileStatement {
    public WhileStatement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
