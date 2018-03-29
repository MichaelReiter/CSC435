package ir;

import codegen.CodeGenVisitor;
import type.Type;

public class AddOperation extends BinaryOperation {
    public AddOperation(Type type, Temp leftOperand, Temp rightOperand) {
        super(type, leftOperand, rightOperand, "+");
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }
}
