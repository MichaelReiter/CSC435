package ast;

public class FunctionDeclaration extends ASTNode {
    TypeNode tn;
    Identifier id;
    // FormalParameters args;

    public FunctionDeclaration(TypeNode tn, Identifier id/*, FormalParameters args*/) {
        this.tn = tn;
        this.id = id;
        // this.args = args;
    }

    public void accept(Visitor v) {
        v.visit(this);
        System.out.println(this);
    }
}
