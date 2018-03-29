package ir;

import codegen.CodeGenVisitor;

public class StringConstant extends Constant {
    private final String value;

    public StringConstant(String value) {
        this.value = value;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return this.value;
    }
}
