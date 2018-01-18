package ast;

public class StatementList {
    public StatementList() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
