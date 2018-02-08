package type;

import ast.Visitor;

public abstract class Type {
    public abstract String toString();
    public abstract boolean equals(Object o);
    public abstract Type accept(Visitor v);
}
