package type;

public abstract class Type {
    @Override
    public final boolean equals(Object o) {
        return o.toString().equals(this.toString());
    }

    @Override
    public final int hashCode() {
        return this.toString().hashCode();
    }
}
