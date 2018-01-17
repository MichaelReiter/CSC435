package ast;

public class Function {
    public Function() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
