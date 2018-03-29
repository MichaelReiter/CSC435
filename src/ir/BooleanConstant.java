package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;

public class BooleanConstant extends Constant {
    private final boolean value;

    public BooleanConstant(boolean value) {
        this.value = value;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return this.value ? "TRUE" : "FALSE";
    }
}
