package Type;

public abstract class Type {
    public abstract void accept(Visitor v);
    public abstract String toShortString();
    public abstract boolean equals(Object o);
}
