package ast;

public class TypeNode extends ASTNode {
    public TypeNode() {}

    public void accept(Visitor v) {
        v.visit(this);
    }
}
