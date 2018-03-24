package ir;

import codegen.CodeGenVisitor;
import java.lang.StringBuilder;

public class ReturnInstruction extends Instruction {
    private final Temp temp;
    
    public ReturnInstruction() {
        this.temp = null;
    }

    public ReturnInstruction(Temp temp) {
        this.temp = temp;
    }

    public void accept(CodeGenVisitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RETURN");
        if (this.temp != null) {
            sb.append(" ");
            sb.append(this.temp);
        }
        sb.append(";");
        return sb.toString();
    }
}
