package ast;

public class Identifier extends Expression {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
