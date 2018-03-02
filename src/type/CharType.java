package type;

import ast.Visitor;
import ir.Temp;
import ir.TempVisitor;

public class CharType extends Type {
    public CharType() {}

    public String toString() {
        return "char";
    }

    public Type accept(Visitor v) {
        return v.visit(this);
    }
    
    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
