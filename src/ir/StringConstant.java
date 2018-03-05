package ir;

import java.lang.StringBuilder;

public class StringConstant extends Constant {
    private final String value;

    public StringConstant(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\u0022');
        sb.append(this.value);
        sb.append('\u0022');
        return sb.toString();
    }
}
