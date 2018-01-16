package AST;

public class MoreFormals {
    public MoreFormals() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
