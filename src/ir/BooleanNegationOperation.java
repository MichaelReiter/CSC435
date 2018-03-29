package ir;

import codegen.CodeGenVisitor;
import type.BooleanType;

public class BooleanNegationOperation extends UnaryOperation {
    public BooleanNegationOperation(Temp operand) {
        super(new BooleanType(), operand, "!");
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }
}
