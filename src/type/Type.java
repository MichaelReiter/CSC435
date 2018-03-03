package type;

public abstract class Type {
    public abstract String toString();

    public final boolean equals(Object o) {
        return o.toString().equals(this.toString());
    }
}
