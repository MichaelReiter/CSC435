package codegen;

public interface CodeGenVisitable {
    public void accept(CodeGenVisitor v);
}
