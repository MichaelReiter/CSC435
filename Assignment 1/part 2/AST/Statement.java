package ast;

public class Statement {
    public Statement() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
