package ir;

import java.lang.StringBuilder;

public class CharacterConstant extends Constant {
    private final char value;

    public CharacterConstant(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u0027");
        sb.append(this.value);
        sb.append("\u0027");
        return sb.toString();
    }
}
