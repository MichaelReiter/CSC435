import ast.AddExpression;
import ast.ArrayAssignmentStatement;
import ast.ArrayReference;
import ast.ArrayReferenceExpression;
import ast.ArrayType;
import ast.AssignmentStatement;
import ast.Block;
import ast.BooleanLiteral;
import ast.CharacterLiteral;
import ast.Declaration;
import ast.EmptyStatement;
import ast.EqualityExpression;
import ast.ExpressionList;
import ast.ExpressionStatement;
import ast.FloatLiteral;
import ast.FormalParameters;
import ast.Function;
import ast.FunctionBody;
import ast.FunctionCall;
import ast.FunctionDeclaration;
import ast.Identifier;
import ast.IdentifierExpression;
import ast.IfElseStatement;
import ast.IfStatement;
import ast.IntegerLiteral;
import ast.LessThanExpression;
import ast.MultiplyExpression;
import ast.ParenthesisExpression;
import ast.PrintlnStatement;
import ast.PrintStatement;
import ast.Program;
import ast.ReturnStatement;
import ast.StatementList;
import ast.StringLiteral;
import ast.SubtractExpression;
import ast.TypeNode;
import ast.VariableDeclaration;
import ast.WhileStatement;
import environment.Environment;
import type.Type;

public class TypeCheckVisitor implements TypeVisitor {
    private Environment<String, FunctionDeclaration> functionEnvironment;
    private Environment<String, Type> variableEnvironment;

    private String currentFunction;
    private Type currentFunctionReturnType;

    public Type visit(AddExpression e) {

    }

    public Type visit(ArrayAssignmentStatement s) {

    }

    public Type visit(ArrayReference a) {

    }

    public Type visit(ArrayReferenceExpression a) {

    }

    public Type visit(ArrayType a) {

    }

    public Type visit(AssignmentStatement a) {

    }

    public Type visit(Block b) {

    }

    public Type visit(BooleanLiteral b) {

    }

    public Type visit(CharacterLiteral c) {

    }

    public Type visit(Declaration d) {

    }

    public Type visit(EmptyStatement e) {

    }

    public Type visit(EqualityExpression e) {

    }

    public Type visit(ExpressionList e) {

    }

    public Type visit(ExpressionStatement e) {

    }

    public Type visit(FloatLiteral f) {

    }

    public Type visit(FormalParameters p) {

    }

    public Type visit(Function f) {

    }

    public Type visit(FunctionBody f) {

    }

    public Type visit(FunctionCall f) {

    }

    public Type visit(FunctionDeclaration f) {

    }

    public Type visit(Identifier i) {

    }

    public Type visit(IdentifierExpression i) {

    }

    public Type visit(IfElseStatement i) {

    }

    public Type visit(IfStatement i) {

    }

    public Type visit(IntegerLiteral i) {

    }

    public Type visit(LessThanExpression e) {

    }

    public Type visit(MultiplyExpression e) {

    }

    public Type visit(ParenthesisExpression p) {

    }

    public Type visit(PrintlnStatement s) {

    }

    public Type visit(PrintStatement s) {

    }

    public Type visit(Program p) {

    }

    public Type visit(ReturnStatement s) {

    }

    public Type visit(StatementList sl) {

    }

    public Type visit(StringLiteral s) {

    }

    public Type visit(SubtractExpression e) {

    }

    public Type visit(Type t) {

    }

    public Type visit(TypeNode t) {

    }

    public Type visit(VariableDeclaration v) {

    }

    public Type visit(WhileStatement s) {

    }
}
