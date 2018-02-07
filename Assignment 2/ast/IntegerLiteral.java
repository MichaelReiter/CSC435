package ast;

public class IntegerLiteral extends Literal {
    int value;

    public IntegerLiteral(int value) {
        this.value = value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
