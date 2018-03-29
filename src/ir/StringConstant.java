package ir;

import codegen.CodeGenVisitor;

public class StringConstant extends Constant<String> {
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
