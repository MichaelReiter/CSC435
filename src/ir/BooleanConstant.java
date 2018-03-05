package ir;

import java.lang.StringBuilder;

public class BooleanConstant extends Constant {
    private final boolean value;

    public BooleanConstant(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value ? "TRUE" : "FALSE";
    }
}
