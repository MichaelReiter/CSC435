package ast;

public class EmptyStatement extends Statement {
    public EmptyStatement() {}

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
