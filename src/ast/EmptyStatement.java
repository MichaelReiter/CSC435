package ast;

public class EmptyStatement extends Statement {
    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
