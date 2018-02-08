package ast;

public class Identifier extends Expression {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }
}
