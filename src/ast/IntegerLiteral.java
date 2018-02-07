package ast;

public class IntegerLiteral extends Literal {
    private int value;

    public IntegerLiteral(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
