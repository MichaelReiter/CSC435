package type;

public class ArrayType extends Type {
    private final Type type;
    private final int size;

    public ArrayType(Type type, int size) {
        this.type = type;
        this.size = size;
    }

    public Type getType() {
        return this.type;
    }

    public String toString() {
        return this.type.toString() + "[" + this.size + "]" ;
    }
}
