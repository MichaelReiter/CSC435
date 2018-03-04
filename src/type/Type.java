package type;

public abstract class Type {
    public char toChar() {
        if (this.equals(new BooleanType())) {
            return 'Z';
        } else if (this.equals(new CharType())) {
            return 'C';
        } else if (this.equals(new FloatType())) {
            return 'F';
        } else if (this.equals(new IntegerType())) {
            return 'I';
        } else if (this.equals(new StringType())) {
            return 'U';
        } else if (this.equals(new VoidType())) {
            return 'V';
        } else {
            assert false : "Should never get here";
            return 'A';
        }
    }

    @Override
    public final boolean equals(Object o) {
        return o.toString().equals(this.toString());
    }

    @Override
    public final int hashCode() {
        return this.toString().hashCode();
    }
}
