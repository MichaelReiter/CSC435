package ir;

import codegen.CodeGenVisitor;

public class FloatConstant extends Constant {
    private final float value;

    public FloatConstant(float value) {
        this.value = value;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }
}
