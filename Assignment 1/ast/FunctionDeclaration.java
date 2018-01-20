package ast;

public class FunctionDeclaration extends ASTNode {
    TypeNode tn;
    // Identifier name;
    // FormalParameters args;

    public FunctionDeclaration(TypeNode tn/*, Identifier name, FormalParameters args*/) {
        this.tn = tn;
        // this.name = name;
        // this.args = args;
    }

    public void accept(Visitor v) {
        v.visit(this);
        System.out.println(this);
    }
}
