package type;

import ast.ASTNode;
import ast.Visitor;

public abstract class Type extends ASTNode {
    public abstract String toString();
    public abstract boolean equals(Object o);
}
