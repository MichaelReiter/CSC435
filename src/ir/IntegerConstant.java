package ir;

import codegen.CodeGenVisitor;

public class IntegerConstant extends Constant<Integer> {
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
