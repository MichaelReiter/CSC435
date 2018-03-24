package codegen;

import ir.AssignmentInstruction;
import ir.ConditionalGotoInstruction;
import ir.Function;
import ir.FunctionCallInstruction;
import ir.Label;
import ir.PrintInstruction;
import ir.PrintlnInstruction;
import ir.ReturnInstruction;
import ir.UnconditionalGotoInstruction;

public interface CodeGenVisitor {
    public void visit(AssignmentInstruction a);
    public void visit(ConditionalGotoInstruction c);
    public void visit(Function f);
    public void visit(FunctionCallInstruction f);
    public void visit(Label l);
    public void visit(PrintInstruction p);
    public void visit(PrintlnInstruction p);
    public void visit(ReturnInstruction r);
    public void visit(UnconditionalGotoInstruction u);
}
