package ast;

public class FunctionBody {
    public FunctionBody() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
