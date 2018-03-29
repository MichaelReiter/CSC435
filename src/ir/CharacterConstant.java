package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;

public class CharacterConstant extends Constant<Character> {
    public CharacterConstant(char value) {
        this.value = value;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
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
