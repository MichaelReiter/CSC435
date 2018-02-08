package semantic;

public class SemanticException extends RuntimeException {
    private String message;
    private int line;
    private int offset;

    public SemanticException(String message, int line, int offset) {
        this.message = message;
        this.line = line;
        this.offset = offset;
    }

    public String toString() {
        return "Error on line " + line + "(" + offset + "): " + message;
    }
}
