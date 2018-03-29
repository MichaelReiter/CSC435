package ir;

import codegen.CodeGenVisitor;

public class IntegerConstant extends Constant {
    private final int value;

    public IntegerConstant(int value) {
        this.value = value;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
