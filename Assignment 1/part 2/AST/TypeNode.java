package ast;

public class TypeNode {
    public TypeNode() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
