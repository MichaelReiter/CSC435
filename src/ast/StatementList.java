package ast;

import java.util.ArrayList;
import java.util.List;

public class StatementList extends ASTNode {
    private final List<Statement> statementList;

    public StatementList() {
        this.statementList = new ArrayList<Statement>();
    }

    public List<Statement> getStatements() {
        return this.statementList;
    }

    public void addStatement(Statement s) {
        this.statementList.add(s);
    }

    public <T> T accept(Visitor<T> v) {
        return v.visit(this);
    }
}
