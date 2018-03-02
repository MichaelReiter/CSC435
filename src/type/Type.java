package type;

import ast.ASTNode;
import ast.Visitor;
import ir.Temp;
import ir.TempVisitor;

public abstract class Type extends ASTNode {
    public abstract String toString();

    public final boolean equals(Object o) {
        return o.toString().equals(this.toString());
    }

    public Temp accept(TempVisitor v) {
        return v.visit(this);
    }
}
