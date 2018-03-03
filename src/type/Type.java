package type;

import ast.ASTNode;
import ast.Visitor;

public abstract class Type extends ASTNode {
    public abstract String toString();

    public final boolean equals(Object o) {
        return o.toString().equals(this.toString());
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
