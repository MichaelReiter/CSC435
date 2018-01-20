package ast;

public class Identifier extends Expression {
    String name;

    public Identifier(String name) {
        this.name = name;
    }

    public void accept(Visitor v) {
        v.visit(this);
        System.out.println(this);
    }
}
